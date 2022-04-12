package screens.tools

import OutlinedTextFieldFolder
import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.myapplication.tools.DateParser
import theme.BluePastel
import theme.DarkBlue
import java.util.*

@Composable
fun datePickerTextField(
    context: Context,
    calendar: Calendar,
    enabled: Boolean,
    width: Dp,
    label: String
): Calendar {
    val widthPicker = 50.dp
    val widthField: Dp = if (enabled) width - widthPicker
    else {
        width
    }
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    val oldDate = Calendar.getInstance()
    oldDate.set(year, month, day)
    val date = remember { mutableStateOf(oldDate) }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, y: Int, m: Int, d: Int ->
            oldDate.set(y, m, d)
            date.value = oldDate
        }, year, month, day
    )
    Row() {
        TextField(
            value = DateParser.convertToString(date.value),
            onValueChange = {
            },
            modifier = Modifier.width(widthField),
            enabled = false,
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = DarkBlue,
                backgroundColor = Color.White,
                focusedIndicatorColor = DarkBlue, //hide the indicator
                unfocusedIndicatorColor = BluePastel
            ),
            label = { Text(text = label) },
        )
        if (enabled) {
            IconButton(
                onClick = { datePickerDialog.show() },
                Modifier.width(widthPicker).padding(top = 20.dp)
            ) {
                Icon(
                    Icons.Filled.DateRange,
                    "contentDescription",
                )
            }
        }
    }
    return date.value
}

@Composable
fun datePickerOutlined(
    context: Context,
    calendar: Calendar
): Calendar {

    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    val oldDate = Calendar.getInstance()
    oldDate.set(year, month, day)
    val date = remember { mutableStateOf(oldDate) }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, y: Int, m: Int, d: Int ->
            oldDate.set(y, m, d)
            date.value = oldDate
        }, year, month, day
    )
    Row() {
        OutlinedTextFieldFolder(
            value = DateParser.convertToString(date.value),
            onValueChange = {
            },
            enabled = false,
            label = { Text(text = "Date") },
        )
        IconButton(
            onClick = { datePickerDialog.show() },
            Modifier.width(50.dp).padding(top = 20.dp)
        ) {
            Icon(
                Icons.Filled.DateRange,
                "contentDescription",
            )
        }
    }
    return date.value
}