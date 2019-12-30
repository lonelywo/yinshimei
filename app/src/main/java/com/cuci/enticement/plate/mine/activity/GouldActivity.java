package com.cuci.enticement.plate.mine.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.cuci.enticement.R;
import com.cuci.enticement.base.BaseActivity;
import com.cuci.enticement.plate.mine.adapter.ItemGould2ViewBinder;
import com.cuci.enticement.plate.mine.adapter.ItemGouldViewBinder;
import com.cuci.enticement.utils.AMapLocUtils;
import com.cuci.enticement.utils.FToast;
import com.cuci.enticement.utils.ViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;


public class GouldActivity extends BaseActivity implements
        PoiSearch.OnPoiSearchListener, AMap.OnCameraChangeListener, ItemGouldViewBinder.OnItemClickListener,ItemGould2ViewBinder.OnItemClickListener {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_biaoti)
    TextView textBiaoti;
    @BindView(R.id.con_toubu)
    ConstraintLayout conToubu;
    MapView mapView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_search)
    TextView btnSearch;
    @BindView(R.id.input_edittext)
    EditText inputEdittext;
    @BindView(R.id.search_bar_layout)
    RelativeLayout searchBarLayout;
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.con_fragment1)
    ConstraintLayout conFragment1;
    @BindView(R.id.recycler_view2)
    RecyclerView recyclerView2;
    @BindView(R.id.con_fragment2)
    ConstraintLayout conFragment2;
    @BindView(R.id.img_clean)
    ImageView imgClean;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mlocationOption;
    AMap aMap;
    private int currentPage = 0;// 当前页面，从0开始计数
    private String keyWord = "";
    private String city = "广州市";
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;
    private PoiResult poiResult; // poi返回的结果
    private List<PoiItem> poiItems;// poi数据
    private LatLonPoint lp = new LatLonPoint(39.993167, 116.473274);
    private MultiTypeAdapter mAdapter;
    private Items mItems;
    private LinearLayoutManager mLayoutManager;
    private Marker locationMarker;
    private LatLng mFinalChoosePosition; //最终选择的点
    private MarkerOptions mMarkerOption; //最终选择的点
    private ProgressDialog progDialog = null;// 搜索时进度条
    private MultiTypeAdapter mAdapter2;
    private Items mItems2;
    private int type;
    private LinearLayoutManager mLayoutManager2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gould;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写

        mAdapter = new MultiTypeAdapter();
        mItems = new Items();
        mAdapter.setItems(mItems);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.register(PoiItem.class, new ItemGouldViewBinder(this));
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        mAdapter2 = new MultiTypeAdapter();
        mItems2 = new Items();
        mAdapter2.setItems(mItems);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        mAdapter2.register(PoiItem.class, new ItemGould2ViewBinder(this));
        mLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(mLayoutManager2);
        recyclerView2.setAdapter(mAdapter);
        new AMapLocUtils().getLonLat(this, new AMapLocUtils.LonLatListener() {
            @Override
            public void getLonLat(AMapLocation aMapLocation) {
                double lon = aMapLocation.getLongitude();
                double lat = aMapLocation.getLatitude();
                city = aMapLocation.getCity();
                lp = new LatLonPoint(lat, lon);
            }
        });
        //初始化定位
        init();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            //地图加载监听器
            aMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() {
                @Override
                public void onMapLoaded() {
                    //aMap.setMapType();
                    aMap.setMyLocationEnabled(true);
                    // 点击之后，我利用代码指定的方式改变了地图中心位置，所以也会调用 onCameraChangeFinish
                    // 只要地图发生改变，就会调用 onCameraChangeFinish ，不是说非要手动拖动屏幕才会调用该方法
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mFinalChoosePosition.latitude, mFinalChoosePosition.longitude), 20));

                }
            });
            aMap.setOnCameraChangeListener(this);// 对amap添加移动地图事件监听器
            MyLocationStyle myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
            myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
            myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                    .fromResource(R.drawable.point4));// 设置小蓝点的图标
            myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
            myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色
            aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
            aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
            aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        }
        // 只要地图发生改变，就会调用 onCameraChangeFinish ，不是说非要手动拖动屏幕才会调用该方法
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lp.getLatitude(), lp.getLongitude()), 20));

    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * 开始进行poi搜索
     */
    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery() {
        type=1;
        currentPage = 0;
        query = new PoiSearch.Query(keyWord, "", city);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页
        LatLonPoint lpTemp = convertToLatLonPoint(mFinalChoosePosition);
        if (lpTemp != null) {
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener((PoiSearch.OnPoiSearchListener) this);
            poiSearch.setBound(new PoiSearch.SearchBound(lpTemp, 5000, true));//
            // 设置搜索区域为以lp点为圆心，其周围5000米范围
            poiSearch.searchPOIAsyn();// 异步搜索
        }
    }

    @Override
    public void onPoiSearched(PoiResult result, int rcode) {
        dissmissProgressDialog();// 隐藏对话框
        if (rcode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    poiResult = result;
                    poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                    if (poiItems != null && poiItems.size() > 0) {
                        if(type==1){
                            mItems.clear();
                            mItems.addAll(poiItems);
                            mAdapter.notifyDataSetChanged();
                        }
                        if(type==2){
                            mItems2.clear();
                            mItems2.addAll(poiItems);
                            mAdapter2.notifyDataSetChanged();
                        }

                    } else if (suggestionCities != null
                            && suggestionCities.size() > 0) {
                    }
                }
            } else {
                FToast.warning("对不起，没有搜索到相关数据！");
            }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        // 添加当前坐标覆盖物
        if (mMarkerOption == null) {
            mMarkerOption = new MarkerOptions()
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                    .position(cameraPosition.target)
                    .draggable(true);
            locationMarker = aMap.addMarker(mMarkerOption);
        } else {
            locationMarker.setPosition(cameraPosition.target);
        }
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        mFinalChoosePosition = cameraPosition.target;
        doSearchQuery();
    }

    /**
     * 把LatLng对象转化为LatLonPoint对象
     */
    public static LatLonPoint convertToLatLonPoint(LatLng latlon) {
        return new LatLonPoint(latlon.latitude, latlon.longitude);
    }


    @Override
    public void onClick(PoiItem bean, int position) {
        String provinceName = bean.getProvinceName();
        String cityName = bean.getCityName();
        String adName = bean.getAdName();
        Intent intent = new Intent(this, ZengAddressActivity.class);
        intent.putExtra("bean", bean);
        setResult(101, intent);
        finish();
    }

    /**
     * 点击搜索按钮
     */
    public void searchButton() {
        keyWord = inputEdittext.getText().toString();
        if ("".equals(keyWord)) {
            FToast.warning("请输入搜索关键字");
            return;
        } else {
            ViewUtils.showView(conFragment2);
            ViewUtils.hideView(conFragment1);
            doSearchQuery1();
        }
    }

    /**
     * 开始进行输入框poi搜索
     */
    protected void doSearchQuery1() {
        showProgressDialog();// 显示进度框
        type=2;
        currentPage = 0;
        query = new PoiSearch.Query(keyWord, "", city);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页
        query.setCityLimit(true);
        PoiSearch  poiSearch2 = new PoiSearch(this, query);
        poiSearch2.setOnPoiSearchListener(this);
        poiSearch2.searchPOIAsyn();
    }

    /**
     * 显示进度框
     */
    private void showProgressDialog() {
        if (progDialog == null)
            progDialog = new ProgressDialog(this);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(false);
        progDialog.setMessage("正在搜索:\n" + keyWord);
        progDialog.show();
    }

    /**
     * 隐藏进度框
     */
    private void dissmissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }

    @OnClick({R.id.btn_search, R.id.img_clean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                searchButton();
                break;
            case R.id.img_clean:
                ViewUtils.showView(conFragment1);
                ViewUtils.hideView(conFragment2);
                break;
        }
    }
}
