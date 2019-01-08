package com.example.shosho.almorshed.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.almorshed.R;
import com.example.shosho.almorshed.View.ContactView;
import com.example.shosho.almorshed.api.Client;
import com.example.shosho.almorshed.api.Service;
import com.example.shosho.almorshed.model.ContactResponse;
import com.example.shosho.almorshed.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactPresenter {
    Context context;
    ContactView contactView;

    public ContactPresenter(Context context, ContactView contactView) {
        this.context = context;
        this.contactView = contactView;
    }

    public void  getContactResult(User user)
    {
        Map<String,String> map=new HashMap<>(  );
        {
            map.put( "name",user.getName() );
            map.put( "email",user.getEmail() );
            map.put( "subject",user.getSubject() );
            map.put( "msg",user.getMsg() );

            Service service=Client.getClient().create( Service.class );
            Call<ContactResponse> call =service.getContactData(  map);
            call.enqueue( new Callback<ContactResponse>() {
                @Override
                public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                    if (response.isSuccessful())
                    {

                            contactView.showContactResult( response.body().getData(),
                                    response.body().getData(),
                                    response.body().getData(),
                                    response.body().getData());


                    }
                    else
                    {
                        contactView.showError("");
                    }
                }

                @Override
                public void onFailure(Call<ContactResponse> call, Throwable t) {
                    Toast.makeText( context, "لا تتواجد بيانات",
                            Toast.LENGTH_SHORT).show();
                }
            } );
        }
    }
}
