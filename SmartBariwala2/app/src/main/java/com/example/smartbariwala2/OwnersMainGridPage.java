package com.example.smartbariwala2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class OwnersMainGridPage extends AppCompatActivity {

    FirebaseAuth mAuth;
    AlertDialog.Builder builder;

    String name [] = {"Add Bills","Show Bills","Update Bills","Clear Bills(All)","Clear Bills(step)","Bill History","Clear Bill's History","Panic Button","To-Let Advertisement","Complain Box","Clear Complain","Change security password","Renters Info","Delete Renters Info","Renters Info History","Clear Renter's History","Setup Unit Password","Sign Out"};
    int img [] = {R.drawable.money,R.drawable.receipt,R.drawable.update,R.drawable.clearall,R.drawable.clear,R.drawable.billist,R.drawable.clearlist,R.drawable.warning,R.drawable.rent,R.drawable.bad,R.drawable.trash,R.drawable.security,R.drawable.contact,R.drawable.remove,R.drawable.billist,R.drawable.cancel,R.drawable.security,R.drawable.logout};

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_owners_main_grid_page);

        getSupportActionBar().setTitle("Exit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gridView = (GridView) findViewById(R.id.grid);
        mAuth = FirebaseAuth.getInstance();

        final CustomAdapter adapter = new CustomAdapter(OwnersMainGridPage.this,name,img);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String name1 = name[i];

                if (name1.equals("Setup Unit Password"))
                {
                    Intent intent = new Intent(getApplicationContext(),SecurityPassOwnerRenterSetupPass.class);
                    startActivity(intent);
                }

                if (name1.equals("Add Bills"))
                {
                    Intent intent = new Intent(getApplicationContext(),SecurityPass2.class);
                    startActivity(intent);
                }

                if (name1.equals("Show Bills"))
                {
                    Intent intent = new Intent(getApplicationContext(),OwnersShowBills.class);
                    startActivity(intent);
                }

                if (name1.equals("Update Bills"))
                {
                    Intent intent = new Intent(getApplicationContext(),SecurityPassUpdateBills.class);
                    startActivity(intent);
                }

                if (name1.equals("Clear Bills(All)"))
                {
                    clearAllBills();
                }

                if (name1.equals("Clear Bill's History"))
                {
                    clearAllBillsList();
                }

                if (name1.equals("Bill History"))
                {
                    Intent intent = new Intent(getApplicationContext(),SecurityPassBillList.class);
                    startActivity(intent);
                }

                if (name1.equals("Clear Bills(step)"))
                {
                    Toast.makeText(getApplicationContext(),"Under Maintanance",Toast.LENGTH_SHORT).show();

                }

                if (name1.equals("Panic Button"))
                {
                    Toast.makeText(getApplicationContext(),"Under Maintanance",Toast.LENGTH_SHORT).show();
                }

                if (name1.equals("Complain Box"))
                {
                    Intent intent = new Intent(getApplicationContext(),ShowComplainSecurityPass.class);
                    startActivity(intent);
                }

                if (name1.equals("Clear Complain"))
                {
                    clearComplain();

                }

                if (name1.equals("To-Let Advertisement"))
                {
                    Intent intent = new Intent(getApplicationContext(),OwnersAddTolet.class);
                    startActivity(intent);
                }

                if (name1.equals("Renters Info"))
                {
                    Intent intent = new Intent(getApplicationContext(),RentersInfoShowSecurityPass.class);
                    startActivity(intent);
                }

                if (name1.equals("Delete Renters Info"))
                {
                    deleteRentersInfo();

                }

                if (name1.equals("Renters Info History"))
                {
                    clearRentersHistory();

                }

                if (name1.equals("Clear Renter's History"))
                {
                        Intent intent = new Intent(getApplicationContext(),SecurityPassClearRentersHistory.class);
                    startActivity(intent);
                }

                if (name1.equals("Change security password"))
                {


                    changeRentersPass();

                }


                if (name1.equals("Sign Out"))

                {
                    signoutApp();

                }
            }


        });

    }


    private void clearRentersHistory() {


        builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.warningicon);
        builder.setTitle("Warning");
        builder.setMessage("Please read carefully before proceed with this action. After successfully removing renter's info list the app will be closed for a successfull data recovery. Please open the app again after this operation. Are you sure to proceed?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                Intent intent = new Intent(getApplicationContext(),SecurityPassRentersInfoHistory.class);
                startActivity(intent);

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void deleteRentersInfo() {

        builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.warningicon);
        builder.setTitle("Warning");
        builder.setMessage("Please read carefully before proceed with this action. After successfully removing renter's info the app will be closed for a successfull data recovery. Please open the app again after this operation. Are you sure to proceed?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {



                Intent intent = new Intent(getApplicationContext(),SecurityPassRemoveRenter.class);
                startActivity(intent);

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void changeRentersPass() {

        builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.warningicon);
        builder.setTitle("Warning");
        builder.setMessage("Please read carefully before proceed with this action. After successfully changing password the app will be closed for a successfull data recovery. Please open the app again after this operation. Are you sure to proceed?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                Intent intent = new Intent(getApplicationContext(),SecurityPassChangeRentersPass.class);
                startActivity(intent);

            }

        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void clearAllBillsList() {

        builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.warningicon);
        builder.setTitle("Warning");
        builder.setMessage("Please read carefully before proceed with this action. After successfully removing bill's the app will be closed for a successfull data recovery. Please open the app again after this operation. Are you sure to proceed?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                Intent intent = new Intent(getApplicationContext(),SecurityPassBillListClear.class);
                startActivity(intent);

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void signoutApp() {

        builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.warningicon);
        builder.setTitle("Warning");
        builder.setMessage("Are you sure to sign out?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                mAuth.signOut();
                Toast.makeText(getApplicationContext(),"Successfully Signed Out",Toast.LENGTH_SHORT).show();


                finish();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void clearAllBills() {

        builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.warningicon);
        builder.setTitle("Warning");
        builder.setMessage("Please read carefully before proceed with this action. After successfully removing bill's the app will be closed for a succesfull data recovery. Please open the app again after this operation. Are you sure to proceed?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent(getApplicationContext(),SecurityPassClearAll.class);
                startActivity(intent);

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void clearComplain() {

        builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.warningicon);
        builder.setTitle("Warning");
        builder.setMessage("Please read carefully before proceed with this action. After successfully removing complain's the app will be closed for a succesfull data recovery. Please open the app again after this operation. Are you sure to proceed?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent(getApplicationContext(),ClearComplainSecurityPass.class);
                startActivity(intent);

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
