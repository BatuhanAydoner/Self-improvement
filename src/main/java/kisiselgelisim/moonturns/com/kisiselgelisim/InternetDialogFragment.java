package kisiselgelisim.moonturns.com.kisiselgelisim;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class InternetDialogFragment extends DialogFragment implements View.OnClickListener {

    private TextView txtOkay;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        setCancelable(false);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.internet_dialog_fragment,null,false);

        txtOkay = (TextView) view.findViewById(R.id.txtOkay);
        txtOkay.setOnClickListener(this);

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(view);

        return dialog;

    }


    //this text close fragment
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.txtOkay:

                dismiss();
                getActivity().onBackPressed();

            break;

        }

    }
}
