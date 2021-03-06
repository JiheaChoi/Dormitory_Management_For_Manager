package com.example.jcproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jcproject.Adapter.ChatAdapter;
import com.example.jcproject.bean.ChatBean;
import com.example.jcproject.bean.ToolBean;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ToolDetailActivity extends AppCompatActivity {

    TextView txtMember, txtContents, txtTitle;
    ListView lstChat;
    Button uploadChat,btnModify;
    EditText edtChat;
    Spinner spinner;
    public static ToolBean toolBean;

    private FirebaseDatabase mDatabase;

    private List<ChatBean> mChatList = new ArrayList<>();
    private ChatAdapter mChatAdapter;
    private ChatBean chatBean;
    private String category;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_detail);

        spinner = findViewById(R.id.Spinner);

        btnModify = findViewById(R.id.btnModify);
        mDatabase = FirebaseDatabase.getInstance();
        intent = getIntent();
        toolBean = (ToolBean) intent.getSerializableExtra(ToolBean.class.getName());

        txtContents=findViewById(R.id.txtContents);
        txtTitle = findViewById(R.id.txtTitle);

        uploadChat = findViewById(R.id.btnUploadChat);

        lstChat = findViewById(R.id.lstChat);
        edtChat = findViewById(R.id.edtChat);


        txtContents.setText(toolBean.contents);
        txtTitle.setText(toolBean.title);

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = spinner.getSelectedItem().toString();
                DatabaseReference firebaseRef = mDatabase.getReference();

                toolBean.category = category;
                toolBean.contents = txtContents.getText().toString();
                toolBean.title = txtTitle.getText().toString();

                firebaseRef.child("tool").child("1").child(toolBean.id).setValue(toolBean);
                finish();
            }
        });

        uploadChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });

        // ??????




        DatabaseReference chat = mDatabase.getReference().child("chat");

        if(chat!=null){
            chat.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //??????????????? ????????? ????????? ????????? ?????? ?????? ????????????.

                    //?????? ???????????? ???????????????.
                    if(mChatList!=null) {
                        mChatList.clear();
                    }

                    //???????????? ??????????????? ??? ???????????? ?????? ?????????.
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String str = snapshot.getKey();
                        if(str.toString().equals(toolBean.id)) {
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                ChatBean bean = snapshot1.getValue(ChatBean.class);
                                mChatList.add(bean);
                            }
                        }
                    }

                    //????????? ??????
                    mChatAdapter = new ChatAdapter(ToolDetailActivity.this, mChatList);
                    lstChat.setAdapter(mChatAdapter);
                    setListViewHeightBasedOnChildren(lstChat);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


            });


        }
    }   //end onCreate()

    void upload(){
        //????????? ???????????? ????????? ???????????? CallBack ?????????
        //????????? ??? : Uploaded??? ????????? URL??? ???????????? ?????????
        //????????? ????????? RealTime DB??? ????????? ?????????.
        DatabaseReference firebaseRef = mDatabase.getReference();
        String id = firebaseRef.push().getKey();

        //DATABASE ??? ????????????.
        ChatBean mChatBean = new ChatBean();

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Calendar time = Calendar.getInstance();
        String format_time1 = format.format(time.getTime());
        mChatBean.textId = toolBean.id;
        mChatBean.reId= id;

        mChatBean.time = format_time1;
        mChatBean.contents =  edtChat.getText().toString();



        firebaseRef.child("chat").child(mChatBean.textId).child(mChatBean.reId).setValue(mChatBean);
        Toast.makeText(ToolDetailActivity.this, "?????? ????????? ??????", Toast.LENGTH_SHORT).show();

        edtChat.setText("");



    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


}

