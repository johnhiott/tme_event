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
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import tme.transactthis.com.couponquest.model.ICoupon;
import tme.transactthis.com.couponquest.model.inmar.vo.Coupon;

public class CouponAdapter extends ArrayAdapter<ICoupon> {
    private final Context context;
    private final List<ICoupon> values;

    public CouponAdapter(Context context, List<ICoupon> values) {
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
}


