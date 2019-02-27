package com.project.ttec.testproject;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by yooyongsuk on 2017. 12. 24..
 */

public class Calendartest extends AppCompatActivity {

    private GridAdapter gridAdapter;

    private ArrayList<String> dayList;
    private ArrayList<String> dayGradeList;

    private TextView text_todaydate;
    private ImageView imgview_selected_grade;
    private Button btn_calendarLeft;
    private Button btn_calendarRight;
    private GridView grid_calendar;

    private Calendar mCal;

    int sYear, sMonth, todayYear, todayMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendartest);

        text_todaydate = (TextView)findViewById(R.id.text_todaydate);
        imgview_selected_grade = (ImageView)findViewById(R.id.imgview_selected_grade);
        btn_calendarLeft = (Button)findViewById(R.id.btn_calendarLeft);
        btn_calendarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sMonth = sMonth -1;
                if(sMonth == 0){
                    sYear = sYear -1;
                    sMonth = 12;
                }

                dayList.clear();
                dayGradeList.clear();
                String monthstr;
                if(sMonth < 10)
                    monthstr = "0" + sMonth;
                else
                    monthstr = String.valueOf(sMonth);
                text_todaydate.setText(sYear + "년" + monthstr + "월");

                setCalendarDate(sYear, sMonth);

                gridAdapter.notifyDataSetChanged();
            }
        });
        btn_calendarRight = (Button)findViewById(R.id.btn_calendarRight);
        btn_calendarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sMonth = sMonth +1;
                if(sMonth == 13){
                    sYear = sYear +1;
                    sMonth = 1;
                }

                dayList.clear();
                dayGradeList.clear();
                String monthstr;
                if(sMonth < 10)
                    monthstr = "0" + sMonth;
                else
                    monthstr = String.valueOf(sMonth);
                text_todaydate.setText(sYear + "년" + monthstr + "월");

                setCalendarDate(sYear, sMonth);

                gridAdapter.notifyDataSetChanged();
            }
        });
        grid_calendar = (GridView)findViewById(R.id.grid_calendar);


        long now = System.currentTimeMillis();

        final Date date = new Date(now);

        //연,월,일을 따로 저장
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);


        text_todaydate.setText(curYearFormat.format(date) + "년" + curMonthFormat.format(date) + "월");


        dayList = new ArrayList<String>();
        dayGradeList = new ArrayList<String>();

        mCal = Calendar.getInstance();


        todayYear = Integer.parseInt(curYearFormat.format(date));
        todayMonth = mCal.get(Calendar.MONTH) + 1;
        setCalendarDate(Integer.parseInt(curYearFormat.format(date)), mCal.get(Calendar.MONTH) + 1);



        gridAdapter = new GridAdapter(getApplicationContext());
        grid_calendar.setAdapter(gridAdapter);


    }

    /**
     * 해당 월에 표시할 일 수 구함
     *
     * @param month
     */
    private void setCalendarDate(int year, int month) {
        sYear = year;
        sMonth = month;

        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
        mCal.set(year, month - 1, 1);
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        for (int i = 1; i < dayNum; i++) {
            dayList.add("");
            dayGradeList.add("");
        }

        mCal.set(Calendar.MONTH, month - 1);

        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            dayList.add(String.valueOf(i + 1));
            if(i == 3 || i == 5 || i == 10)
                dayGradeList.add("s");
            else
                dayGradeList.add("");
        }

    }


    /**
     * 그리드뷰 어댑터
     *
     */
    private class GridAdapter extends BaseAdapter {

//        private final List<String> list;
        private final LayoutInflater inflater;

        /**
         * 생성자
         *
         * @param context
//         * @param list
         */

        public GridAdapter(Context context) {//, List<String> list) {

//            this.list = list;
            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return dayList.size();
        }

        @Override
        public String getItem(int position) {
            return dayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
                holder = new ViewHolder();

                holder.llCalendarItem = (LinearLayout)convertView.findViewById(R.id.ll_calendar_item);
                holder.textItemDay = (TextView)convertView.findViewById(R.id.text_item_day);
                holder.imgviewGrade = (ImageView)convertView.findViewById(R.id.imgview_grade);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.textItemDay.setTextColor(Color.rgb(0, 0, 0));
            holder.llCalendarItem.setId(position);

            holder.textItemDay.setText(getItem(position));
            if(position==0 || position==7 || position==14 || position==21 || position==28 || position==35)
                holder.textItemDay.setTextColor(Color.rgb(237, 125, 125));
            if(position==6 || position==13 || position==20 || position==27 || position==34 || position==41)
                holder.textItemDay.setTextColor(Color.rgb(62, 109, 203));

            //해당 날짜 텍스트 컬러,배경 변경
            mCal = Calendar.getInstance();
            //오늘 day 가져옴
            Integer today = mCal.get(Calendar.DAY_OF_MONTH);
            String sToday = String.valueOf(today);
            if(todayYear == sYear && todayMonth == sMonth) {
                if (sToday.equals(getItem(position))) //오늘 day 텍스트 컬러 변경
                    holder.textItemDay.setTextColor(Color.rgb(101, 123, 229));
            }




            if(dayGradeList.get(position).length() != 0)
                holder.imgviewGrade.setVisibility(View.VISIBLE);
            else
                holder.imgviewGrade.setVisibility(View.GONE);


            holder.llCalendarItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int idx = v.getId();
                    System.out.println("idx === " + idx);
                    System.out.println("idx === " + dayList.get(idx));

                    Resources resources = getApplicationContext().getResources();
                    int main_img = resources.getIdentifier("mission_record_coachs", "drawable", getApplicationContext().getPackageName());
                    Drawable home_drawable = ContextCompat.getDrawable(getApplicationContext(), main_img);
                    imgview_selected_grade.setImageDrawable(home_drawable);

                }
            });

            return convertView;

        }

    }



    private class ViewHolder {
        LinearLayout llCalendarItem;
        TextView textItemDay;
        ImageView imgviewGrade;
    }



}
