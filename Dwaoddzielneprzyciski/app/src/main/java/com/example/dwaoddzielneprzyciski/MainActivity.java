package com.example.dwaoddzielneprzyciski;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private LocalDateTime dateTime = LocalDateTime.now();
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDate = findViewById(R.id.btnDate);
        Button btnTime = findViewById(R.id.btnTime);
        tvResult = findViewById(R.id.tvResult);

        refreshText();

        btnDate.setOnClickListener(v -> showDatePicker());
        btnTime.setOnClickListener(v -> showTimePicker());
    }

    private void showDatePicker() {
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                (view, y, m, d) -> {
                    dateTime = dateTime.withYear(y).withMonth(m + 1).withDayOfMonth(d);
                    refreshText();
                },
                dateTime.getYear(),
                dateTime.getMonthValue() - 1,
                dateTime.getDayOfMonth()
        );
        dialog.show();
    }

    private void showTimePicker() {
        TimePickerDialog dialog = new TimePickerDialog(
                this,
                (view, h, m) -> {
                    dateTime = dateTime.withHour(h).withMinute(m);
                    refreshText();
                },
                dateTime.getHour(),
                dateTime.getMinute(),
                true
        );
        dialog.show();
    }

    private void refreshText() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        tvResult.setText(dateTime.format(f));
    }
}
