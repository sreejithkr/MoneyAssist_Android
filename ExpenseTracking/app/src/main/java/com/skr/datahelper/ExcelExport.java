package com.skr.datahelper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import com.skr.AppController;
import com.skr.expensetrack.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Created by sreejithkr on 25/08/15.
 */
public class ExcelExport {

    final static String income = "Income";
    final static String expense = "Expense";

    public static void openFolder(Context context,String path)
    {
        File file = new File(path);
//        Intent viewIntent = new Intent(Intent.ACTION_VIEW);
//        Intent editIntent = new Intent(Intent.ACTION_EDIT);
//        viewIntent.setDataAndType(Uri.fromFile(file), "application/xls");
//        editIntent.setDataAndType(Uri.fromFile(file), "application/xls");
//        Intent chooserIntent = Intent.createChooser(viewIntent, "Open in...");
//        chooserIntent.putExtra(Intent.EXTRA_SUBJECT, "Shared from the ActionBar widget.");
//
//        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { viewIntent });
//        context.startActivity(chooserIntent);


        PackageManager pm = context.getPackageManager();
        Intent viewIntent = new Intent(Intent.ACTION_VIEW);
        Intent editIntent = new Intent(Intent.ACTION_EDIT);
        viewIntent.setDataAndType(Uri.fromFile(file), "application/xls");
        editIntent.setDataAndType(Uri.fromFile(file), "application/xls");
        Intent openInChooser = Intent.createChooser(viewIntent, "Open in...");

// Append " (for editing)" to applicable apps, otherwise they will show up twice identically
        Spannable forEditing = new SpannableString(" (for editing)");
        forEditing.setSpan(new ForegroundColorSpan(Color.CYAN), 0, forEditing.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        List<ResolveInfo> resInfo = pm.queryIntentActivities(editIntent, 0);
        Intent[] extraIntents = new Intent[resInfo.size()];
        if(resInfo.size() == 0){

            Log.e("","NO APPS POSSIBLE");
        }
        for (int i = 0; i < resInfo.size(); i++) {
            // Extract the label, append it, and repackage it in a LabeledIntent
            ResolveInfo ri = resInfo.get(i);
            String packageName = ri.activityInfo.packageName;
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
            intent.setAction(Intent.ACTION_EDIT);
            editIntent.setDataAndType(Uri.fromFile(file), "application/xls");
            CharSequence label = TextUtils.concat(ri.loadLabel(pm), forEditing);
            extraIntents[i] = new LabeledIntent(intent, packageName, label, ri.icon);
        }

        openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
        context.startActivity(openInChooser);

    }
    public static String saveToExcel(Context context,ArrayList<ExpenseIncome>expenseIncomes,HashMap<Integer,String>categoryHashMap){

        try {
            String expenseIncomeDetails = context.getString(R.string.expenseIncomeDetails);
            File sdCard = Environment.getExternalStorageDirectory();
            Date date = new Date();

            String fileName = expenseIncomeDetails+"_"+date.toString()+".xls";
            File dir = new File(sdCard.getAbsolutePath() + "/"+expenseIncomeDetails);
            dir.mkdirs();
            File file = new File(dir, fileName);
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            WritableSheet writablesheetForIncome = workbook.createSheet(income, 0);
            WritableSheet writablesheetForExpence = workbook.createSheet(expense, 1);
            String EXPENCE_INCOME_SL_NO = "Sl No"  ;
            String CATEGORY =  "CATEGORY";
            String AMOUNT = "AMOUNT";
            String DESCRIPTION  = "DESCRIPTION";
            String DATE = "DATE";
            Label label = new Label(0, 0, EXPENCE_INCOME_SL_NO);
            WritableCell cell =  label;
            writablesheetForIncome.addCell(cell);
            label = new Label(0, 0, EXPENCE_INCOME_SL_NO);
            cell =  label;
            writablesheetForExpence.addCell(cell);
            label = new Label(1, 0, CATEGORY);
             cell =  label;
            writablesheetForIncome.addCell(cell);
            label = new Label(1, 0, CATEGORY);
            cell =  label;
            writablesheetForExpence.addCell(cell);

            label = new Label(2, 0, AMOUNT);
             cell =  label;
            writablesheetForIncome.addCell(cell);
            label = new Label(2, 0, AMOUNT);
            cell =  label;
            writablesheetForExpence.addCell(cell);
            label = new Label(3, 0, context.getString(R.string.CURRENCY));
            cell =  label;
            writablesheetForIncome.addCell(cell);
            label = new Label(3, 0, context.getString(R.string.CURRENCY));
            cell =  label;
            writablesheetForExpence.addCell(cell);
            label = new Label(4, 0, DATE);
             cell =  label;
            writablesheetForIncome.addCell(cell);
            label = new Label(4, 0, DATE);
            cell =  label;
            writablesheetForExpence.addCell(cell);
            label = new Label(5, 0, DESCRIPTION);
            cell =  label;
            writablesheetForIncome.addCell(cell);
            label = new Label(5, 0, DESCRIPTION);
            cell =  label;
            writablesheetForExpence.addCell(cell);

Integer row_num_income = 0;
            Integer row_num_expence = 0;
            Log.e("Exception 123 BEfore fore loop"," count "+0);
            for(int count = 0; count<expenseIncomes.size();count++) {
                int row = count;

                Log.e("Exception 123 BEfore"," count "+count+expenseIncomes.toString());
                ExpenseIncome expenseIncome = expenseIncomes.get(count);
                Log.e("Exception 123 expenseIncome"," count "+count+expenseIncomes.toString());
                if(expenseIncome.IF_EXPENSE){
                    row_num_expence++;
                    row = row_num_expence;
                }else{
                    row_num_income++;
                    row = row_num_income;
                }
                label = new Label(0, row, row+"");
                cell =  label;
                if(expenseIncome.IF_EXPENSE){
                    writablesheetForExpence.addCell(cell);
                }else{
                    writablesheetForIncome.addCell(cell);
                }
                label = new Label(1, row, categoryHashMap.get(expenseIncome.getCATEGORY_ID()));
                cell =  label;

                if(expenseIncome.IF_EXPENSE){
                    writablesheetForExpence.addCell(cell);
                }else{
                    writablesheetForIncome.addCell(cell);
                }
                label = new Label(2, row, expenseIncome.getAMOUNT().toString());
                cell =  label;
                if(expenseIncome.IF_EXPENSE){
                    writablesheetForExpence.addCell(cell);
                }else{
                    writablesheetForIncome.addCell(cell);
                }
                label = new Label(3, row, AppController.getCurrencyString());
                cell =  label;
                if(expenseIncome.IF_EXPENSE){
                    writablesheetForExpence.addCell(cell);
                }else{
                    writablesheetForIncome.addCell(cell);
                }
                label = new Label(4, row, expenseIncome.getDateinMMMM_DD_YYYY());
                cell =  label;
                if(expenseIncome.IF_EXPENSE){
                    writablesheetForExpence.addCell(cell);
                }else{
                    writablesheetForIncome.addCell(cell);
                }
                label = new Label(5, row, expenseIncome.getDESCRIPTION());
                cell =  label;
                if(expenseIncome.IF_EXPENSE){
                    writablesheetForExpence.addCell(cell);
                }else{
                    writablesheetForIncome.addCell(cell);
                }


            }
            workbook.write();
            workbook.close();
            return  file.getPath();
        }catch (Exception e){
            Log.e("Exception 123",""+e.toString());
            return  "";

        }

    }
}
