package com.example.storemanagement.util

import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.WorkbookUtil
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.InputStream

class ExcelUtil {

    fun readExcelNew(context: Context, uri: Uri?, filePath: String?): List<Map<Int, Any>>? {
        var list: List<Map<Int, Any>>? = null
        var wb: Workbook?
        if (filePath == null) {
            return null
        }
        val extString: String = filePath.substring(filePath.lastIndexOf("."))
        val isStream: InputStream?
        try {
            isStream = uri?.let { context.contentResolver.openInputStream(it) }
            Log.i(TAG, "readExcel: $extString")
            wb = when (extString) {
                ".xls" -> {
                    org.apache.poi.hssf.usermodel.HSSFWorkbook(isStream)
                }
                ".xlsx" -> {
                    XSSFWorkbook(isStream)
                }
                else -> {
                    null
                }
            }
            if (wb != null) {
                list = ArrayList()
                val sheet: Sheet = wb.getSheetAt(0)
                val rowHeader: Row = sheet.getRow(0)
                val cellsCount: Int = rowHeader.physicalNumberOfCells
                val headerMap: MutableMap<Int, Any> = HashMap()
                for (c in 0 until cellsCount) {
                    val value: Any? = getCellFormatValue(rowHeader.getCell(c))
                    val cellInfo = "header; c:$c; v:$value"
                    Log.i(TAG, "readExcelNew: $cellInfo")
                    headerMap[c] = value as Any
                }
                list.add(headerMap)
                val rownum: Int = sheet.physicalNumberOfRows
                val colnum: Int = headerMap.size
                for (i in 1 until rownum) {
                    val row: Row? = sheet.getRow(i)
                    val itemMap: MutableMap<Int, Any> = HashMap()
                    if (row != null) {
                        for (j in 0 until colnum) {
                            val value: Any? = getCellFormatValue(row.getCell(j))
                            val cellInfo = "r: $i; c:$j; v:$value"
                            Log.i(TAG, "readExcelNew: $cellInfo")
                            itemMap[j] = value as Any
                        }
                    } else {
                        break
                    }
                    list.add(itemMap)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "readExcelNew: import error $e")
            Toast.makeText(context, "import error $e", Toast.LENGTH_SHORT).show()
        }
        return list
    }
}

fun writeExcelNew(context: Context, exportExcel: List<Map<Int, Any>>, uri: Uri) {
    try {
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("Sheet1"))

        val columns = exportExcel[0].size
        for (i in 0 until columns) {
            // set the cell default width to 15 characters
            sheet.setColumnWidth(i, 15 * 256)
        }

        for (i in exportExcel.indices) {
            val row = sheet.createRow(i)
            val integerObjectMap = exportExcel[i]
            for (j in 0 until columns) {
                val cell = row.createCell(j)
                cell.setCellValue(integerObjectMap[j].toString())
            }
        }

        val outputStream = context.contentResolver.openOutputStream(uri)
        workbook.write(outputStream)
        outputStream?.flush()
        outputStream?.close()
        Log.i(TAG, "writeExcel: export successful")
        Toast.makeText(context, "export successful", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        e.printStackTrace()
        Log.e(TAG, "writeExcel: error$e")
        Toast.makeText(context, "export error$e", Toast.LENGTH_SHORT).show()
    }
}


/**
 * get single cell data
 *
 * @param cell </>
 * @return cell
 */
fun getCellFormatValue(cell: Cell?): Any? {
    var cellValue: Any? = null
    cell?.let {
        when (it.cellType) {
            CellType.BOOLEAN -> cellValue = it.booleanCellValue
            CellType.NUMERIC -> cellValue = it.numericCellValue.toString()
            CellType.FORMULA -> {
                if (DateUtil.isCellDateFormatted(it)) {
                    cellValue = it.dateCellValue
                } else {
                    cellValue = it.numericCellValue.toString()
                }
            }
            CellType.STRING -> cellValue = it.richStringCellValue.string
            else -> cellValue = ""
        }
    } ?: run {
        cellValue = ""
    }
    return cellValue
}
