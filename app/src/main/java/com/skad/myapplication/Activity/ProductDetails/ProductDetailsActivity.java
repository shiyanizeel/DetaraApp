package com.skad.myapplication.Activity.ProductDetails;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.skad.myapplication.Activity.HomeActivity.CartActivity;
import com.skad.myapplication.Activity.HomeActivity.MenuNavDetailsActivity;
import com.skad.myapplication.Models.ApiModel.Image;
import com.skad.myapplication.Models.ApiModel.Product;
import com.skad.myapplication.Models.ApiModel.ProductDetailsModel;
import com.skad.myapplication.R;
import com.skad.myapplication.helper.sharedPref.SharedPreferencesHelper;

public class ProductDetailsActivity extends AppCompatActivity {
    ImageView itemImage, itemShowArrowBack;
    LinearLayout itemSpinnerContain4, itemSpinnerContain5, itemSpinnerContain6, itemSpinnerContain7, itemSpinnerContain8;
    TextView itemRingName, itemRingPrice, addToCartBtn, colorTV, metalTV, sizeTv, caretTV, weightTV;
    private boolean isDescriptionVisible1 = false;
    private boolean isDescriptionVisible2 = false;
    private boolean isDescriptionVisible3 = false;
    private boolean isColorVisible = false;
    ImageView productShareDetails;
    ImageView arrowDownColor, arrowDownSize, arrowDownMetal, arrowDownCaret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // Initialize views
        itemShowArrowBack = findViewById(R.id.itemShowArrowBack);
        itemImage = findViewById(R.id.itemImage);
        itemRingName = findViewById(R.id.itemRingName);
        itemRingPrice = findViewById(R.id.itemRingPrice);
        arrowDownColor = findViewById(R.id.arrowDownColor);
        colorTV = findViewById(R.id.colorTV);
        arrowDownMetal = findViewById(R.id.arrowDownMetal);
        metalTV = findViewById(R.id.metalTV);
        arrowDownSize = findViewById(R.id.arrowDownSize);
        sizeTv = findViewById(R.id.sizeTV);
        arrowDownCaret = findViewById(R.id.arrowDownCaret);
        caretTV = findViewById(R.id.caretTV);
        weightTV = findViewById(R.id.weightTV);
        addToCartBtn = findViewById(R.id.itemAddcartBtn);
        itemSpinnerContain4 = findViewById(R.id.itemSpinnerContain4);
        itemSpinnerContain5 = findViewById(R.id.itemSpinnerContain5);
        itemSpinnerContain6 = findViewById(R.id.itemSpinnerContain6);
        itemSpinnerContain7 = findViewById(R.id.itemSpinnerContain7);
        itemSpinnerContain8 = findViewById(R.id.itemSpinnerContain8);
        productShareDetails = findViewById(R.id.productShareDetails);

        // Share button functionality
        productShareDetails.setOnClickListener(v -> {
            String itemLink = "https://www.detaragem.com/item";
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this item: " + itemLink);
            startActivity(Intent.createChooser(shareIntent, "Share item via"));
        });

        // Back button functionality
        itemShowArrowBack.setOnClickListener(v -> startActivity(new Intent(ProductDetailsActivity.this, MenuNavDetailsActivity.class)));

        // Get intent data

        Product productDetails =(Product) getIntent().getSerializableExtra("productDetails");
        Image productImage = productDetails.getImage();
        String ringImageUrl = (productImage != null) ? productImage.getSrc() : null;
        int ringOption = productDetails.getOptions().size();
        String ringName = productDetails.getTitle();
        String ringPrice = getIntent().getStringExtra("ringPrice");
        Long productId = getIntent().getLongExtra("productId", 0L);
        Log.e("newSize", "onCreate: " + productId);

        Toast.makeText(this, "option " + ringOption, Toast.LENGTH_SHORT).show();
        if (ringOption <= 1) {
            itemSpinnerContain5.setVisibility(View.INVISIBLE);
            itemSpinnerContain6.setVisibility(View.INVISIBLE);
            itemSpinnerContain7.setVisibility(View.INVISIBLE);
            itemSpinnerContain8.setVisibility(View.INVISIBLE);
        }
        if (ringImageUrl != null && !ringImageUrl.isEmpty()) {
            Glide.with(this).load(ringImageUrl).into(itemImage);
        } else {
            itemImage.setImageResource(R.drawable.detarahead); // Default image if URL is missing
        }

        // Set name and price
        itemRingName.setText(ringName != null ? ringName : "Unknown");
        itemRingPrice.setText(ringPrice != null ? ringPrice : "Price not available");

        // Toggle functionality
        setupDescriptionToggle(R.id.itemSpinnerContain1, R.id.itemFeatureDescription, R.id.arrowDown1Item, 1);
        setupDescriptionToggle(R.id.itemSpinnerContain2, R.id.itemMaterialDescription, R.id.arrowDown2Item, 2);
        setupDescriptionToggle(R.id.itemSpinnerContain3, R.id.itemTipsDescription, R.id.arrowDown3Item, 3);

        // Menu setups
        arrowDownColor.setOnClickListener(v -> colorShow());
        arrowDownMetal.setOnClickListener(v -> metalShow());
        arrowDownSize.setOnClickListener(v -> sizeShow());
        arrowDownCaret.setOnClickListener(v -> caretShow());

        addToCartBtn.setOnClickListener(v -> {
            String selectedColor = colorTV.getText().toString();
            String selectedSize = sizeTv.getText().toString();
            String selectedCaret = caretTV.getText().toString();
            String selectedMetal = metalTV.getText().toString();
            productDetails.setSelectedColor(selectedColor);
            productDetails.setSelectedSize(selectedSize);
            productDetails.setSelectedCaret(selectedCaret);
            productDetails.setSelectedMetal(selectedMetal);
            productDetails.setSelectedWeight("");
            ProductDetailsModel selectedProductDetails = new ProductDetailsModel(
                    productId,
                    ringImageUrl,
                    ringName,
                    ringPrice,
                    selectedColor,
                    selectedSize,
                    selectedCaret,
                    selectedMetal
            );

            Intent intent1 = new Intent(ProductDetailsActivity.this, CartActivity.class);
            intent1.putExtra("product", selectedProductDetails);
            SharedPreferencesHelper.getInstance(getApplicationContext(),"cart").addUser(productDetails);
            System.out.println("product"+selectedProductDetails.getSize());
            startActivity(intent1);
        });
    }

    private void setupDescriptionToggle(int spinnerId, int descriptionId, int arrowId, int descriptionType) {
        View spinner = findViewById(spinnerId);
        TextView description = findViewById(descriptionId);
        ImageView arrow = findViewById(arrowId);

        spinner.setOnClickListener(v -> {
            boolean isVisible;
            if (descriptionType == 1) {
                isVisible = isDescriptionVisible1;
                isDescriptionVisible1 = !isVisible;
            } else if (descriptionType == 2) {
                isVisible = isDescriptionVisible2;
                isDescriptionVisible2 = !isVisible;
            } else {
                isVisible = isColorVisible;
                isColorVisible = !isVisible;
            }

            description.setVisibility(isVisible ? View.GONE : View.VISIBLE);
            arrow.setRotation(isVisible ? 0 : 180);
        });
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    public void colorShow() {
        PopupMenu popupMenu = new PopupMenu(this, arrowDownColor);
        Menu menu = popupMenu.getMenu();
        menu.add(Menu.NONE, 1, 0, "Rose Gold");
        menu.add(Menu.NONE, 2, 1, "Yellow Gold");
        menu.add(Menu.NONE, 3, 2, "White Gold");

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case 1: colorTV.setText("Color:Rose Gold"); break;
                case 2: colorTV.setText("Color:Yellow Gold"); break;
                case 3: colorTV.setText("Color:White Gold"); break;
                default: return false;
            }
            return true;
        });
        popupMenu.show();
    }

    @SuppressLint("SetTextI18n")
    public void metalShow() {
        PopupMenu popupMenu = new PopupMenu(this, arrowDownMetal);
        Menu menu = popupMenu.getMenu();
        menu.add(Menu.NONE, 1, 0, "14K");
        menu.add(Menu.NONE, 2, 1, "18k");

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case 1: metalTV.setText("Metal:14K"); break;
                case 2: metalTV.setText("Metal:18k"); break;
                default: return false;
            }
            return true;
        });
        popupMenu.show();
    }

    @SuppressLint("SetTextI18n")
    public void sizeShow() {
        PopupMenu popupMenu = new PopupMenu(this, arrowDownSize);
        Menu menu = popupMenu.getMenu();
        for (int i = 1; i <= 25; i++) {
            double size = 0.5 * i;
            menu.add(Menu.NONE, i, i - 1, String.valueOf(size));
        }

        popupMenu.setOnMenuItemClickListener(item -> {
            sizeTv.setText("Size:" + (0.5 * item.getItemId()));
            return true;
        });
        popupMenu.show();
    }

    @SuppressLint("SetTextI18n")
    public void caretShow() {
        PopupMenu popupMenu = new PopupMenu(this, arrowDownCaret);
        Menu menu = popupMenu.getMenu();
        for (int i = 1; i <= 6; i++) {
            double caret = 0.5 * i;
            menu.add(Menu.NONE, i, i - 1, String.valueOf(caret));
        }

        popupMenu.setOnMenuItemClickListener(item -> {
            caretTV.setText("Caret:" + (0.5 * item.getItemId()));
            return true;
        });
        popupMenu.show();
    }
}
