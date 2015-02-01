package com.example.yyy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	MatrixHandler instance = new MatrixHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btn = (Button)findViewById(R.id.buttonCalc);
      //  EditText
        final TextView viw = (TextView)findViewById(R.id.result);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String obeseMatrix = ((EditText)findViewById(R.id.editText1)).getText().toString(); 
				Boner result = instance.getResult(obeseMatrix);
				int LowerBound = result.LB;
				String reducedMatrix = instance.ReducedMatrixAsString(result.matrix);
				viw.setText(reducedMatrix+"\n\nLowerBound = "+LowerBound);
			
			}
		});
    }
}
