package com.example.mary.inventoryapp.data;

import android.provider.BaseColumns;

/**
 * API Contract for the Store app.
 */
public class StoreContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private StoreContract() {}

    /**
     * Inner class that defines constant values for the store database table.
     * Each entry in the table represents a single product.
     */
    public static final class ProductEntry implements BaseColumns {

        /** Name of database table for products */
        public final static String TABLE_NAME = "products";

        /**
         * Unique ID number for the products (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Product name.
         *
         * Type: TEXT
         */
        public final static String COLUMN_PRODUCT_NAME ="ProductName";

        /**
         * Price of the product.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_PRICE = "Price";

        /**
         * Quantity of the product.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_QUANTITY = "Quantity";

        /**
         * Supplier Name of the product.
         *
         * Type: TEXT
         */
        public final static String COLUMN_SUPPLIER_NAME = "SupplierName";

        /**
         * Supplier Phone Number.
         *
         * Type: LONG
         */
        public final static String COLUMN_SUPPLIER_PHONE = "SupplierPhoneNumber";
    }

}

