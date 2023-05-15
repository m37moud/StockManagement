package com.example.storemanagement.util

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.example.storemanagement.MainActivity
import com.example.storemanagement.data.database.entity.CategoryEntity
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.WorkbookUtil
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.InputStream

class ExcelUtil {
    private val TAG = ExcelUtil::class.java.simpleName


    fun readExcelNew(context: Context, uri: Uri?, filePath: String?)
//    : List<Map<String, Any>>? {
            : List<CategoryEntity>? {
        Log.d(TAG, "readExcel: readExcelNew call")
        Log.d("ExcelUtil", "readExcel: readExcelNew call tag = $TAG")


        var list: List<CategoryEntity>? = null
//        var list = mutableListOf<CategoryEntity>()
        var wb: Workbook?
        var isStream: InputStream?

        try {
            if (filePath == null) {
                return null
            }
            val extString: String = filePath.substring(filePath.lastIndexOf("."))
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
                    Log.i(TAG, "readExcelNew cellInfo: $cellInfo")
                    headerMap[c] = value as Any
                }
//                list.add(headerMap)
                val rownum: Int = sheet.physicalNumberOfRows
                val colnum: Int = headerMap.size
                for (i in 1 until rownum) {
                    val row: Row? = sheet.getRow(i)
                    val itemMap: MutableMap<String, CategoryEntity> = HashMap()
                    val status = mutableMapOf<String, Any>()
                    if (row != null) {
                        for (j in 0 until colnum) {
                            val value: Any? = getCellFormatValue(row.getCell(j))
                            val cellInfo = "r: $i; c:$j; v:$value"
                            Log.i(TAG, "readExcelNew: $cellInfo")
                            when (j) {
                                0 -> {
                                    status["id"] = value as Any


                                }

                                1 -> { // category
                                    status["category"] = value as Any
                                }
                            }
//                            itemMap[j] = value as Any
                        }
                    } else {
                        break
                    }
                    Log.i(TAG, "readExcelNew status: ${status["id"].toString().split(".")[0].toInt()} +${status["category"]} ")

                    val category = CategoryEntity(
                        status["id"].toString().split(".")[0].toInt(),
                        categoryName = status["category"].toString()
                    )
                    list.add(category)
//                        .apply {
////                        itemMap["content"] =  this
//                        list.add(this)
//                    }

                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "readExcelNew: import error $e")
//            Toast.makeText(context, "import error $e", Toast.LENGTH_SHORT).show()
        } finally {
            wb = null
            isStream = null

        }
        return list
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
    private fun getCellFormatValue(cell: Cell?): Any? {
        var cellValue: Any? = null
        cell?.let {
            cellValue = when (it.cellType) {
                CellType.BOOLEAN -> it.booleanCellValue
                CellType.NUMERIC -> it.numericCellValue.toString()
                CellType.FORMULA -> {
                    if (DateUtil.isCellDateFormatted(it)) {
                        it.dateCellValue
                    } else {
                        it.numericCellValue.toString()
                    }
                }
                CellType.STRING -> it.richStringCellValue.string
                else -> ""
            }
        } ?: run {
            cellValue = ""
        }
        return cellValue
    }

}

