package com.rentcentric.paperlesscounter.Activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.rentcentric.paperlesscounter.Models.Requests.Signature;
import com.rentcentric.paperlesscounter.Models.Responses.SignatureSetup;
import com.rentcentric.paperlesscounter.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SignaturesAdapter extends RecyclerView.Adapter<SignaturesAdapter.SignaturesViewHolder> {

    private ArrayList<SignatureSetup> signaturesList;
    private Context context;

    // for count
    static Set<Integer> setOfSignedSignatures;

    static private Signature signature;

    // <index, signature>
    public Map<Integer, Signature> signaturesMap;

    public SignaturesAdapter(ArrayList<SignatureSetup> signaturesList, Context context) {
        this.signaturesList = signaturesList;
        this.context = context;
        setOfSignedSignatures = new HashSet<>();

        // init
        signaturesMap = new HashMap<>();
        for(int i = 0 ; i < signaturesList.size() ; i++){
            signaturesMap.put(i, null);
        }
    }

    @NonNull
    @Override
    public SignaturesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SignaturesViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_signatures_initials, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final SignaturesViewHolder signaturesViewHolder, int i) {
        // set item text
        if (!signaturesList.get(i).getDisclaimer().isEmpty()) {
            signaturesViewHolder.tvDisclaimer.setText(signaturesList.get(i).getDisclaimer());
        }

        // set signature
//        if (signaturesMap.get(i) != null) {
//            byte[] encodeByte = Base64.decode(signaturesMap.get(i).getSignatureImage(), Base64.DEFAULT);
//            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//            signaturesViewHolder.spSignature.setSignatureBitmap(bitmap);
//        }

        signaturesViewHolder.spSignature.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                if (signaturesViewHolder.spSignature.getTransparentSignatureBitmap(true) != null) {
                    // add to counter
                    setOfSignedSignatures.add(signaturesList.get(i).getSignatureSetupID());
                    // add to signatures
                    ByteArrayOutputStream bao = new ByteArrayOutputStream();
                    signaturesViewHolder.spSignature.getTransparentSignatureBitmap(true).compress(Bitmap.CompressFormat.PNG, 100, bao);
                    byte[] signatureByteArray = bao.toByteArray();
                    signature = new Signature();
                    signature.setSignatureImage(Base64.encodeToString(signatureByteArray, Base64.DEFAULT));
                    signature.setSignatureSetupID(signaturesList.get(i).getSignatureSetupID());
                    signaturesMap.put(i, signature);

                    Log.v("signo", signaturesMap.toString() + " || " + setOfSignedSignatures.toString());
                }
            }

            @Override
            public void onClear() {
                signaturesMap.put(i, null);

                if (setOfSignedSignatures.size() > 0)
                    setOfSignedSignatures.remove(signaturesList.get(i).getSignatureSetupID());

                Log.v("signo", signaturesMap.toString() + " || " + setOfSignedSignatures.toString());
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return signaturesList.size();
    }

    static class SignaturesViewHolder extends RecyclerView.ViewHolder {
        // Data
        int position;

        // UI
        TextView tvDisclaimer;
        SignaturePad spSignature;
        Button btnClear;

        SignaturesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDisclaimer = itemView.findViewById(R.id.CardSignaturesInitials_TV_Disclaimer);
            spSignature = itemView.findViewById(R.id.CardSignaturesInitials_SP);
            btnClear = itemView.findViewById(R.id.btn_clear);

            btnClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spSignature.clear();
                }
            });
        }


    }
}