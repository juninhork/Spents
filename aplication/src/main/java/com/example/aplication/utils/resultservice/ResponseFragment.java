package com.example.aplication.utils.resultservice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aplication.R;
import com.example.aplication.utils.Utils;

public class ResponseFragment extends Fragment implements View.OnClickListener {

    public static ResponseService itsPayResponse;
    public static IResponseSevice iView;
    private Animation animFadeIn;
    private TextView textMenssagem;
    private TextView textDetalhe;
    private LinearLayout layoutOkService;
    private LinearLayout layoutErrorService;
    private ImageView ivCloseView;

    public static ResponseFragment newInstance(ResponseService responseService, IResponseSevice iResponseSevice) {
        ResponseFragment fragment = new ResponseFragment();
        itsPayResponse = responseService;
        iView = iResponseSevice;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_response, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.textMenssagem = (TextView) view.findViewById(R.id.text_menssagem);
        this.textDetalhe = (TextView) view.findViewById(R.id.text_detalhe);
        this.layoutOkService = (LinearLayout) view.findViewById(R.id.layout_ok_service);
        this.layoutErrorService = (LinearLayout) view.findViewById(R.id.layout_error_service);
        this.ivCloseView = (ImageView) view.findViewById(R.id.iv_close_view);

        this.animFadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        this.ivCloseView.setOnClickListener(this);

        this.initData();
    }

    private void initData() {
        if (this.itsPayResponse.isSuccess()) {
            this.layoutOkService.setVisibility(View.VISIBLE);
            this.layoutErrorService.setVisibility(View.GONE);
            this.layoutOkService.setAnimation(animFadeIn);
            this.alterarCor(R.color.sucssed);
        } else {
            this.layoutOkService.setVisibility(View.GONE);
            this.layoutErrorService.setVisibility(View.VISIBLE);
            this.alterarCor(R.color.error);
            this.layoutErrorService.setAnimation(animFadeIn);
        }
        this.textMenssagem.setAnimation(animFadeIn);
        this.textDetalhe.setText(itsPayResponse.getDtl());
        this.textMenssagem.setText(itsPayResponse.getMsg());
    }

    private void alterarCor(int color) {
        this.textMenssagem.setTextColor(getActivity().getResources().getColor(color));
        this.textDetalhe.setTextColor(getActivity().getResources().getColor(color));
        this.ivCloseView.setColorFilter(getActivity().getResources().getColor(color));
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_close_view) {
            Utils.removeFragment(this, getActivity().getSupportFragmentManager());
            this.iView.showButon(this.itsPayResponse.isSuccess());

        }
    }

}
