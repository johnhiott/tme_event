package tme.transactthis.com.couponquest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import tme.transactthis.com.couponquest.model.ICoupon;
import tme.transactthis.com.couponquest.model.inmar.vo.Coupon;

public class CouponAdapter extends ArrayAdapter<ICoupon> {
    private final Context context;
    private final List<ICoupon> values;

    //lol been awake for 24 hours.. lets do this.
    private List<View> animateViews;
    private List<ImageView> animateLocks;

    public CouponAdapter(Context context, List<ICoupon> values) {
        super(context, R.layout.coupon_item, values);
        this.context = context;
        this.values = values;
        animateViews = new ArrayList<View>();
        animateLocks = new ArrayList<ImageView>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView textView = null;
        ImageView imageView = null;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.coupon_item, parent, false);

        //brand
        textView = (TextView) rowView.findViewById(R.id.brand);
        textView.setText( values.get(position).getBrand() );

        //image
        imageView = (ImageView) rowView.findViewById(R.id.image);
        Picasso.with(context).load( values.get(position).getImageUrl() ).into(imageView);

        //value
        textView = (TextView) rowView.findViewById( R.id.value );
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        float num = (float) values.get(position).getValue()/100;
        textView.setText( fmt.format( num ) );

        //min
        textView = (TextView) rowView.findViewById( R.id.min );
        textView.setText( "Min: " + values.get(position).getMinPurchase() );

        View bgview  =  rowView.findViewById( R.id.lockBackground );
        animateViews.add(bgview);

        ImageView lock = (ImageView) rowView.findViewById( R.id.lock );
        animateLocks.add(lock);
        if(values.get(position).isLocked()){
            bgview.setVisibility(View.VISIBLE);
            lock.setVisibility(View.VISIBLE);
        } else {
            bgview.setVisibility(View.GONE);
            lock.setVisibility(View.GONE);
        }

        //expires
        textView = (TextView) rowView.findViewById( R.id.expires );

        String[] strings = values.get(position).getExpirationDate().split("T");
        String date = strings[0];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
        Date newDate;
        try {
            newDate = sdf.parse( date );
            sdf.applyPattern("MM/dd/yyyy");
            String newDateString = sdf.format(newDate);
            textView.setText( "Expires: " + newDateString );
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return rowView;
    }

    public ImageView unlockNext(){
        for(ICoupon coupon : values){
            if(coupon.isLocked()){
                ImageView lock = animateLocks.get(values.indexOf(coupon));
                return lock;
            }

        }
        return null;
    }
    public View unlockNextView(){
        for(ICoupon coupon : values){
            if(coupon.isLocked()){
                //unlock(values.indexOf(coupon));
                View view = animateViews.get(values.indexOf(coupon));
                return view;
            }

        }
        return null;
    }

    public void setNextUnlocked(){
        for(ICoupon coupon : values){
            if(coupon.isLocked()){
               coupon.setLocked(false);
               break;
            }

        }
    }

}


