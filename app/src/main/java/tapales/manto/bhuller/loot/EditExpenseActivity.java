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

public class EditExpenseActivity extends AppCompatActivity{
    private Expense currentExpense;
    private Toolbar toolbar;
    private DatabaseOpenHelper dbHelper;
    private int mYear, mMonth, mDay;
    private TextView categoryItem, dateText;
    private Button dateButton, submitButton, cancelButton;
    private ImageButton floatingActionBar;
    private EditText editTitle, editValue;
    private TextInputLayout editLayoutTitle, editLayoutValue;
    private ImageView foodButton, leisureButton, transportButton, billButton, debtButton, othersButton;
    private static final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Edit Expense");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        dbHelper = new DatabaseOpenHelper(getBaseContext());
        currentExpense = dbHelper.getExpense(getIntent().getExtras().getInt(Expense.COL_ID));
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        categoryItem = (TextView) findViewById(R.id.edit_category);
        categoryItem.setText("Category - " + currentExpense.getCategory());
        dateText = (TextView) findViewById(R.id.edit_date);
        dateText.setText("Date - " + currentExpense.getDate());
        editTitle = (EditText) findViewById(R.id.edit_expense_title);
        editTitle.setText(currentExpense.getExpName());
        editValue = (EditText) findViewById(R.id.edit_expense_value);
        editValue.setText(String.valueOf(currentExpense.getSpentAmount()));
        editLayoutTitle = (TextInputLayout) findViewById(R.id.edit_layout_title);
        editLayoutValue = (TextInputLayout) findViewById(R.id.edit_layout_value);
        submitButton = (Button) findViewById(R.id.edit_submit_button);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                currentExpense.setExpName(editTitle.getText().toString());
                currentExpense.setSpentAmount(Float.valueOf(editValue.getText().toString()));
                currentExpense.setCategory(categoryItem.getText().toString().replace("Category -", ""));
                currentExpense.setDate(dateText.getText().toString().replace("Date - ", ""));
                //Temporary 1
                currentExpense.setPaymentType(1);
                dbHelper.updateExpense(currentExpense);
                setResult(RESULT_OK);
                finish();
            }
        });
        cancelButton = (Button) findViewById(R.id.edit_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        dateButton = (Button) findViewById(R.id.edit_date_button);
        dateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog dpd = new DatePickerDialog(EditExpenseActivity.this,
                        new DatePickerDialog.OnDateSetListener(){
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                                dateText.setText("Date - " + months[monthOfYear] + " " + dayOfMonth + ", " + year);
                                Toast.makeText(getBaseContext(), "Date - " + months[monthOfYear] + " " + dayOfMonth + ", " + year, Toast.LENGTH_LONG).show();
                            }}
                        , mYear, mMonth, mDay);
                dpd.show();
            }
        });
        foodButton = (ImageView) findViewById(R.id.edit_food);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getBaseContext(), "Category - Food Selected", Toast.LENGTH_LONG).show();
                categoryItem.setText("Category - Food");
            }
        });
        leisureButton = (ImageView) findViewById(R.id.edit_leisure);
        leisureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getBaseContext(),"Category - Leisure Selected", Toast.LENGTH_LONG).show();
                categoryItem.setText("Category - Leisure");
            }
        });
        transportButton = (ImageView) findViewById(R.id.edit_transportation);
        transportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getBaseContext(),"Category - Transportation Selected", Toast.LENGTH_LONG).show();
                categoryItem.setText("Category - Transportation");
            }
        });
        billButton = (ImageView) findViewById(R.id.edit_bills);
        billButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getBaseContext(),"Category - Bills Selected", Toast.LENGTH_LONG).show();
                categoryItem.setText("Category - Bills");
            }
        });
        debtButton = (ImageView) findViewById(R.id.edit_debt);
        debtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(getBaseContext(),"Category - Debt Selected", Toast.LENGTH_LONG).show();
                categoryItem.setText("Category - Debt");
            }
        });
        othersButton = (ImageView) findViewById(R.id.edit_others);
        othersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Category - Others Selected", Toast.LENGTH_LONG).show();
                categoryItem.setText("Category - Others");
            }
        });
        floatingActionBar = (ImageButton) findViewById(R.id.floating_action_button);
        floatingActionBar.setVisibility(View.INVISIBLE);
    }
}