package com.ftfl.ftflicare.util;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;

import android.content.Context;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;

public class SetDateOnClickDialog implements OnFocusChangeListener,
		OnDateSetListener {

	private EditText mDateEditText;
	private Calendar mMyCalendar;
	private Context mContext;
	public int mDayOfWeek = 0;

	public SetDateOnClickDialog(EditText editText, Context eContext) {
		this.mDateEditText = editText;
		this.mDateEditText.setOnFocusChangeListener(this);
		this.mContext = eContext;
		mMyCalendar = Calendar.getInstance();
		mDayOfWeek = mMyCalendar.get(Calendar.DAY_OF_WEEK);

	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		if (hasFocus) {
			int year = mMyCalendar.get(Calendar.YEAR);
			int monthOfYear = mMyCalendar.get(Calendar.MONTH);
			int dayOfMonth = mMyCalendar.get(Calendar.DAY_OF_MONTH);

			new DatePickerDialog(mContext, this, year, monthOfYear, dayOfMonth)
					.show();

		}
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		String s = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
		mDateEditText.setText(s);

	}

	public String getDayOfMonth(int eDay) {
		String day = "";
		if (eDay == 1) {
			day = "Sunday";
		} else if (eDay == 2) {
			day = "Monday";
		} else if (eDay == 3) {
			day = "Tuesday";
		} else if (eDay == 4) {
			day = "Wednesday";
		} else if (eDay == 5) {
			day = "Thursday";
		} else if (eDay == 6) {
			day = "Friday";
		} else if (eDay == 7) {
			day = "Saturday";
		}
		return day;
	}

}

