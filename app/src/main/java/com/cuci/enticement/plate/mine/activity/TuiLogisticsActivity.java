package com.cuci.enticement.plate.mine.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.caimuhao.rxpicker.RxPicker;
import com.caimuhao.rxpicker.bean.ImageItem;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.TuiImgBean;
import com.cuci.enticement.bean.TuiImgKuangBean;
import com.cuci.enticement.plate.mine.adapter.ItemImgViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemImgkuangViewBinder;
import com.cuci.enticement.utils.ImageUtils;
import com.cuci.enticement.utils.RxImageLoader;
import com.cuci.enticement.widget.BrandItemDecoration;
import com.cuci.enticement.widget.ClearEditText;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class TuiLogisticsActivity extends BaseActivity implements ItemImgkuangViewBinder.OnProdClickListener1,ItemImgViewBinder.OnProdClickListener2 {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_content)
    TextView textContent;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    @BindView(R.id.text_wenzi1)
    TextView textWenzi1;
    @BindView(R.id.edt_phone1)
    ClearEditText edtPhone1;
    @BindView(R.id.con_xuantian1)
    ConstraintLayout conXuantian1;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.text_wenzi2)
    TextView textWenzi2;
    @BindView(R.id.edt_phone2)
    ClearEditText edtPhone2;
    @BindView(R.id.con_xuantian2)
    ConstraintLayout conXuantian2;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.text_wenzi3)
    TextView textWenzi3;
    @BindView(R.id.edt_phone3)
    ClearEditText edtPhone3;
    @BindView(R.id.con_xuantian3)
    ConstraintLayout conXuantian3;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.text_wenzi4)
    TextView textWenzi4;
    @BindView(R.id.edt_phone4)
    ClearEditText edtPhone4;
    @BindView(R.id.con_xuantian4)
    ConstraintLayout conXuantian4;
    @BindView(R.id.text_pingzheng)
    TextView textPingzheng;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.ok)
    TextView ok;
    private List<TuiImgBean> list = new ArrayList<TuiImgBean>();
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private LinearLayoutManager mLayoutManager;
    private String mImagePath;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tui_logistics;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        edtPhone1.setClearIconVisible(false);
        edtPhone2.setClearIconVisible(false);
        edtPhone3.setClearIconVisible(false);
        edtPhone4.setClearIconVisible(false);
        RxPicker.init(new RxImageLoader());
        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        TuiImgKuangBean tuiImgKuangBean = new TuiImgKuangBean();
        mItems.add(tuiImgKuangBean);
        mAdapter.setItems(mItems);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.register(TuiImgKuangBean.class, new ItemImgkuangViewBinder(this));
        mAdapter.register(TuiImgBean.class, new ItemImgViewBinder(this));

        BrandItemDecoration mDecoration = new BrandItemDecoration(this, 3);

        recyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.ok:
                break;
        }
    }

    @Override
    public void onProdClick2(TuiImgBean item) {

    }

    @Override
    public void onProdClick3(int position) {
        TuiImgKuangBean tuiImgKuangBean = new TuiImgKuangBean();
        list.remove(position);
        if (list.size() < 4) {
            mItems.clear();
            mItems.addAll(list);
            mItems.add(tuiImgKuangBean);
        } else if (list.size() == 4) {
            mItems.clear();
            mItems.addAll(list);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onProdClick1(TuiImgKuangBean item) {
        setIcon(true);
    }
    private void setIcon(boolean showCamera) {
        Disposable d = RxPicker.of()
                .single(false)
                .camera(showCamera)
                .limit(1, 4 - list.size())
                .start(this)
                .subscribe(imageItems -> {
                    //得到结果
                    TuiImgKuangBean tuiImgKuangBean = new TuiImgKuangBean();

                    if (imageItems.size() == 0) {
                        return;
                    }
                    for (int i = 0; i < imageItems.size(); i++) {
                        ImageItem imageItem = imageItems.get(i);
                        mImagePath = imageItem.getPath();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Bitmap mBitmap = null;
                                try {
                                    mBitmap = analyzeBitmap(mImagePath);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                TuiImgBean tuiImgBean = new TuiImgBean();
                                tuiImgBean.setImg(mBitmap);
                                list.add(tuiImgBean);

                                String base64Str = ImageUtils.bitmapToBase64(mBitmap);

                            }
                        });
                    }

                    if (list.size() < 4) {
                        mItems.clear();
                        mItems.addAll(list);
                        mItems.add(tuiImgKuangBean);
                    } else if (list.size() == 4) {
                        mItems.clear();
                        mItems.addAll(list);
                    }
                    mAdapter.notifyDataSetChanged();
                });
    }
    /**
     * yasuo
     */
    public Bitmap analyzeBitmap(String path) throws Exception {

        /**
         * 首先判断图片的大小,若图片过大,则执行图片的裁剪操作,防止OOM
         */
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 先获取原大小
        Bitmap mBitmap = BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false; // 获取新的大小

        int sampleSize = (int) (options.outHeight / (float) 400);

        if (sampleSize <= 0)
            sampleSize = 1;
        options.inSampleSize = sampleSize;
        mBitmap = BitmapFactory.decodeFile(path, options);

        //处理图片旋转问题
        ExifInterface exif = null;

        exif = new ExifInterface(path);
        int orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION, 0);
        Matrix matrix = new Matrix();
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
            matrix.postRotate(90);
        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
            matrix.postRotate(180);
        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
            matrix.postRotate(270);
        }

        mBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
                mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);

        return mBitmap;


    }
}
