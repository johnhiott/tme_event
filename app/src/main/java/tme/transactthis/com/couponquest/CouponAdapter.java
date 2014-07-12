package tme.transactthis.com.couponquest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

import tme.transactthis.com.couponquest.model.inmar.vo.Coupon;

public class CouponAdapter extends ArrayAdapter<Coupon> {
    private final Context context;
    private final List<Coupon> values;

    public CouponAdapter(Context context, List<Coupon> values) {
        super(context, R.layout.coupon_item, values);
        this.context = context;
        this.values = values;
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
        textView.setText( values.get(position).brand );

        //image
        imageView = (ImageView) rowView.findViewById(R.id.image);
        Picasso.with(context).load( values.get(position).imageUrl ).into(imageView);

        //value
        textView = (TextView) rowView.findViewById( R.id.value );
        textView.setText( values.get(position).value );

        //min
        textView = (TextView) rowView.findViewById( R.id.min );
        textView.setText( values.get(position).minPurchase );

        //expires
        textView = (TextView) rowView.findViewById( R.id.expires );
        textView.setText( values.get(position).expirationDate.iso );

        return rowView;
    }
}


