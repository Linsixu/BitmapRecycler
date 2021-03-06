package cn.bili.linsixu.commen_base.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

import cn.bili.linsixu.commen_base.R;
import cn.bili.linsixu.commen_base.base.BaseActivity;
import cn.bili.linsixu.commen_base.databinding.ActivityCommentBinding;
import cn.bili.linsixu.commen_base.feed.BaseIndexItem;
import cn.bili.linsixu.commen_base.feed.adapter.IndexAdapter;
import cn.bili.linsixu.commen_base.feed.base.BasicCardCreatorV2;
import cn.bili.linsixu.commen_base.feed.base.PegasusCardManager;
import cn.bili.linsixu.commen_base.feed.item.LargeCoverV1Item;
import cn.bili.linsixu.commen_base.feed.item.SmallerCoverItem;
import cn.bili.linsixu.commen_base.utils.MyLog;
import cn.bili.linsixu.commen_base.utils.PolicyEvent;

/**
 * Created by Magic
 * on 2018/11/22.
 */
@android.support.annotation.RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
@Route(path = "/common/FeedActivity")
public class FeedActivity extends BaseActivity<ActivityCommentBinding> {
    public static final String LAYOUR_FLAG = "layout";
    public static final String TXT_FLAG = "txt";
    final private int mCardCreateType = 0;

    private PegasusCardManager mCardManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initData() {
        PolicyEvent policyEvent = new PolicyEvent(PolicyEvent.EVENT_SOURCE);

        mCardManager = new PegasusCardManager(new BasicCardCreatorV2(""), mCardCreateType);

//        List<BaseIndexItem> indexItems = JSON.parseArray(json, BaseIndexItem.class);
//
//        for (BaseIndexItem t:indexItems) {
//            mCardManager.addCard(mCardManager.createCard(t));
//        }
        LargeCoverV1Item t = new LargeCoverV1Item();
        t.cover = "http://i2.hdslb.com/bfs/archive/0130dca5b602f1a9d503c8821827b0a99d5eefc4.jpg";
        t.feed = "天马测试1";
        t.cardType = "large_cover_v1";

        SmallerCoverItem s = new SmallerCoverItem();
        s.desc = "肯定崩溃";
        s.book = "看书拉";
        s.cardType = "small_cover_v1";

        mCardManager.addCard(mCardManager.createCard(t));
        mCardManager.addCard(mCardManager.createCard(s));
        mCardManager.addCard(mCardManager.createCard(t));
        mCardManager.addCard(mCardManager.createCard(t));
        mCardManager.addCard(mCardManager.createCard(t));
        mCardManager.addCard(mCardManager.createCard(t));
        mCardManager.addCard(mCardManager.createCard(t));
        mCardManager.addCard(mCardManager.createCard(t));

        binding.recycler.setLayoutManager(new LinearLayoutManager(this));

        binding.recycler.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                 MyLog.i("magic", "view=" + view);
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                MyLog.i("magic", "DetachView=" + view);
            }
        });

        IndexAdapter adapter = new IndexAdapter(mCardManager);

        binding.recycler.setAdapter(adapter);

    }

    @Override
    protected void initClick() {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        addTransitionListener();
    }

    private void addTransitionListener() {
        final Transition transition = getWindow().getSharedElementExitTransition();
        final Transition transition1 = getWindow().getSharedElementEnterTransition();
    }
}
