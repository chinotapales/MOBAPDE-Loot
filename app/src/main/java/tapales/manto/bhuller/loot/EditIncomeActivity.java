package tapales.manto.bhuller.loot;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class EditIncomeActivity extends AppCompatActivity{
    private Income currentIncome;
    private Toolbar toolbar;
    private DatabaseOpenHelper dbHelper;
    private int mYear, mMonth, mDay;
    private TextView dateText;
    private Button dateButton, submitButton, cancelButton;
    private ImageButton floatingActionBar;
    private EditText editTitle, editValue;
    private TextInputLayout editLayoutTitle, editLayoutValue;
    private static final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_income);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Edit Income");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        dbHelper = new DatabaseOpenHelper(getBaseContext());
        currentIncome = dbHelper.getIncome(getIntent().getExtras().getInt(Income.COL_ID));
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        dateText = (TextView) findViewById(R.id.edit_income_date);
        dateText.setText("Date - " + currentIncome.getTimeInterval());
        editTitle = (EditText) findViewById(R.id.edit_income_title);
        editTitle.setText(currentIncome.getIncomeName());
        editValue = (EditText) findViewById(R.id.edit_expense_value);
        editValue.setText(String.valueOf(currentIncome.getIncomeAmount()));
        editLayoutTitle = (TextInputLayout) findViewById(R.id.edit_income_layout_title);
        editLayoutValue = (TextInputLayout) findViewById(R.id.edit_income_layout_value);
        submitButton = (Button) findViewById(R.id.edit_income_submit_button);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                currentIncome.setIncomeName(editTitle.getText().toString());
                currentIncome.setIncomeAmount(Float.valueOf(editValue.getText().toString()));
                currentIncome.setTimeInterval(dateText.getText().toString().replace("Date - ", ""));
                dbHelper.updateIncome(currentIncome);
                setResult(RESULT_OK);
                finish();
            }
        });
        cancelButton = (Button) findViewById(R.id.edit_income_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        dateButton = (Button) findViewById(R.id.edit_income_date_button);
        dateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog dpd = new DatePickerDialog(EditIncomeActivity.this,
                        new DatePickerDialog.OnDateSetListener(){
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                                dateText.setText("Date - " + months[monthOfYear] + " " + dayOfMonth + ", " + year);
                                Toast.makeText(getBaseContext(), "Date - " + months[monthOfYear] + " " + dayOfMonth + ", " + year, Toast.LENGTH_LONG).show();
                            }}
                        , mYear, mMonth, mDay);
                dpd.show();
            }
        });
        floatingActionBar = (ImageButton) findViewById(R.id.floating_action_button);
        floatingActionBar.setVisibility(View.INVISIBLE);
    }
}