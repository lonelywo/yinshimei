package com.cuci.enticement.plate.mine.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.caimuhao.rxpicker.RxPicker;
import com.caimuhao.rxpicker.bean.ImageItem;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.bean.AllOrderList;
import com.cuci.enticement.bean.TuiImgBean;
import com.cuci.enticement.bean.TuiImgKuangBean;
import com.cuci.enticement.network.ServiceCreator;
import com.cuci.enticement.plate.mine.adapter.ItemImgViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemImgkuangViewBinder;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.ImageUtils;
import com.cuci.enticement.utils.RxImageLoader;
import com.cuci.enticement.widget.OrderItemDecoration;
import com.cuci.enticement.widget.SmoothScrollview;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import io.reactivex.disposables.Disposable;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;


public class TuiKuanType1Activity extends BaseActivity implements ItemImgkuangViewBinder.OnProdClickListener1, ItemImgViewBinder.OnProdClickListener2 {


    @BindView(R.id.tv_biaoqian)
    TextView tvBiaoqian;
    @BindView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @BindView(R.id.id_editor_detail)
    EditText idEditorDetail;
    @BindView(R.id.id_editor_detail_font_count)
    TextView idEditorDetailFontCount;
    @BindView(R.id.scroll_details)
    SmoothScrollview scrollDetails;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_content)
    TextView textContent;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    //标签
    List<String> list;
    @BindView(R.id.tv_biaoqian1)
    TextView tvBiaoqian1;
    @BindView(R.id.id_flowlayout1)
    TagFlowLayout idFlowlayout1;
    @BindView(R.id.line1)
    View line1;
    //图片
    private List<TuiImgBean> imglist = new ArrayList<>();
    MultiTypeAdapter mmAdapter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.con_edt)
    ConstraintLayout conEdt;
    @BindView(R.id.tv_shuoming)
    TextView tvShuoming;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private String tag;
    private boolean islMaxCount = false;
    private Items mItems;
    private GridLayoutManager mLayoutManager;
    private TagAdapter<String> mAdapter;
    private String mImagePath;
    private AllOrderList.DataBean.ListBeanX mInfo;

    private List<String> list1;
    private String tag1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tui_type1;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        mInfo = (AllOrderList.DataBean.ListBeanX) intent.getSerializableExtra("intentInfo");
        init1();
        init();
        RxPicker.init(new RxImageLoader());
        mmAdapter = new MultiTypeAdapter();
        mItems = new Items();
        TuiImgKuangBean tuiImgKuangBean = new TuiImgKuangBean();
        tuiImgKuangBean.setNum(0);
        mItems.add(tuiImgKuangBean);
        mmAdapter.setItems(mItems);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mmAdapter.register(TuiImgKuangBean.class, new ItemImgkuangViewBinder(this));
        mmAdapter.register(TuiImgBean.class, new ItemImgViewBinder(this));

        OrderItemDecoration mDecoration = new OrderItemDecoration(this, 6);

        recyclerView.addItemDecoration(mDecoration);
        mLayoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(mmAdapter);


        String strMsg = "申请换货/退款/退货退款服务需签署" + "<font color=\"#e1ad73\">" + "《退款协议》" + "</font>" + "，点击提交则默认您已查阅并同意退款协议所有内容";
        tvShuoming.setText(Html.fromHtml(strMsg));
        tvShuoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentProd = new Intent(TuiKuanType1Activity.this, TuiAgreementActivity.class);
                intentProd.putExtra("bannerData", "");
                startActivity(intentProd);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void init1() {
        tag1 = "未收到货";
        final LayoutInflater mInflater = LayoutInflater.from(this);
        list1 = new ArrayList<>();
        list1.add("未收到货");
        list1.add("已收到货");

        /**
         * 点击某个 Tag 返回
         */

        idFlowlayout1.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                // tag = list.get(position).getName();
                // FToast.success(tag);
                return true;
            }
        });
        idFlowlayout1.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

                String s = selectPosSet.toString();
                String quStr = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
                if (TextUtils.equals(s, "[]")) {
                    tag1 = "";
                    FToast.success(tag1);
                } else {
                    Integer integer = Integer.valueOf(quStr);
                    tag1 = list1.get(integer);
                    FToast.success(tag1);
                }
                init();
            }
        });

        idFlowlayout1.setAdapter(mAdapter = new TagAdapter<String>(list1) {
            @Override
            public View getView(FlowLayout parent, int position, String user) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        idFlowlayout1, false);
                tv.setText(user);
                return tv;
            }


        });
        //预先设置选中
        mAdapter.setSelectedList(0);

    }

    @OnTextChanged(value = R.id.id_editor_detail, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void editTextDetailChange(Editable editable) {
        int detailLength = editable.length();
        idEditorDetailFontCount.setText(detailLength + "/200");
        if (detailLength == 199) {
            islMaxCount = true;
        }
        // 不知道为什么执行俩次，所以增加一个标识符去标识
        if (detailLength == 200 && islMaxCount) {
            FToast.warning("您已达到输入字数上限");
            islMaxCount = false;
        }
    }

    private void init() {
        tag = "未按约定时间发货";
        final LayoutInflater mInflater = LayoutInflater.from(this);
        list = new ArrayList<>();
        if(TextUtils.equals(tag1,"已收到货")){
            list.clear();
            list.add("不喜欢/不想要");
            list.add("包装/商品破损");
            list.add("退运费");
            list.add("卖家发错货");
            list.add("其它原因");
        }else {
            list.clear();
            list.add("未按约定时间发货");
            list.add("快递/物流一直未送到");
            list.add("货物破损已经拒签");
            list.add("其它原因");
        }

        /**
         * 点击某个 Tag 返回
         */

        idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                // tag = list.get(position).getName();
                // FToast.success(tag);
                return true;
            }
        });
        idFlowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

                String s = selectPosSet.toString();
                String quStr = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
                if (TextUtils.equals(s, "[]")) {
                    tag = "";
                    FToast.success(tag);
                } else {
                    Integer integer = Integer.valueOf(quStr);
                    tag = list.get(integer);
                    FToast.success(tag);
                }

            }
        });

        idFlowlayout.setAdapter(mAdapter = new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String user) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        idFlowlayout, false);
                tv.setText(user);
                return tv;
            }


        });
        //预先设置选中
        mAdapter.setSelectedList(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onProdClick2(TuiImgBean item) {
        ServiceCreator.Constant_PZ_IMG = item.getImg();
        startActivity(new Intent(this, ImgActivity.class));

    }

    @Override
    public void onProdClick3(int position) {
        TuiImgKuangBean tuiImgKuangBean = new TuiImgKuangBean();
        imglist.remove(position);
        int size = imglist.size();
        if (imglist.size() < 5) {
            mItems.clear();
            mItems.addAll(imglist);
            tuiImgKuangBean.setNum(size);
            mItems.add(tuiImgKuangBean);
        } else if (imglist.size() == 5) {
            mItems.clear();
            mItems.addAll(imglist);
        }
        mmAdapter.notifyDataSetChanged();
    }

    @Override
    public void onProdClick1(TuiImgKuangBean item) {
        setIcon(true);
    }


    private void setIcon(boolean showCamera) {
        Disposable d = RxPicker.of()
                .single(false)
                .camera(showCamera)
                .limit(1, 5 - imglist.size())
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
                                    //    mBitmap = BitmapUitls.getSDCardImg(mImagePath);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                TuiImgBean tuiImgBean = new TuiImgBean();
                                tuiImgBean.setImg(mBitmap);
                                imglist.add(tuiImgBean);

                                String base64Str = ImageUtils.bitmapToBase64(mBitmap);

                            }
                        });

                    }
                    int size = imglist.size();
                    if (imglist.size() < 5) {
                        mItems.clear();
                        mItems.addAll(imglist);
                        tuiImgKuangBean.setNum(size);
                        mItems.add(tuiImgKuangBean);
                    } else if (imglist.size() == 5) {
                        mItems.clear();
                        mItems.addAll(imglist);
                    }
                    mmAdapter.notifyDataSetChanged();
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

        int sampleSize = (int) (options.outHeight / (float) 1000);

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
