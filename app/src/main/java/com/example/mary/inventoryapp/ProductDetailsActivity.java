package com.example.mary.inventoryapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mary.inventoryapp.data.ProductDbHelper;
import com.example.mary.inventoryapp.data.StoreContract;
import com.example.mary.inventoryapp.data.StoreContract.ProductEntry;

/**
 * Allows user to create a new product or edit an existing one.
 */
public class ProductDetailsActivity extends AppCompatActivity {

    /** EditText field to enter the product's name */
    private EditText nameEditText;

    /** EditText field to enter the product's price*/
    private EditText priceEditText;

    /** EditText field to enter the product's quantity */
    private EditText quantityEditText;

    /** EditText field to enter the product's supplier name */
    private EditText supplierNameEditText;

    /** EditText field to enter the product's supplier phone */
    private EditText supplierPhoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details_activity);

        // Find all relevant views that we will need to read user input from
        nameEditText = (EditText) findViewById(R.id.hint_product_name);
        priceEditText = (EditText) findViewById(R.id.hint_product_price);
        quantityEditText = (EditText) findViewById(R.id.hint_product_quantity);
        supplierNameEditText = (EditText) findViewById(R.id.hint_product_supplier_name);
        supplierPhoneEditText = (EditText) findViewById(R.id.hint_product_supplier_phone);
    }

    /**
     * Get user input from editor and save new product into database.
     */
    private void insertProduct() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = nameEditText.getText().toString().trim();
        String priceString = priceEditText.getText().toString().trim();
        int price = Integer.parseInt(priceString);
        String quantityString = quantityEditText.getText().toString().trim();
        int quantity = Integer.parseInt(quantityString);
        String supplierNameString = supplierNameEditText.getText().toString().trim();
        String supplierPhoneString = supplierPhoneEditText.getText().toString().trim();

        // Create database helper
        ProductDbHelper productDbHelper = new ProductDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = productDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and product attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_PRODUCT_NAME, nameString);
        values.put(ProductEntry.COLUMN_PRICE, price);
        values.put(ProductEntry.COLUMN_QUANTITY, quantity);
        values.put(ProductEntry.COLUMN_SUPPLIER_NAME, supplierNameString);
        values.put(ProductEntry.COLUMN_SUPPLIER_PHONE, supplierPhoneString);

        // Insert a new row for products in the database, returning the ID of that new row.
        long newRowId = db.insert(ProductEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving product", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Product saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_product_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save product to database
                insertProduct();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}