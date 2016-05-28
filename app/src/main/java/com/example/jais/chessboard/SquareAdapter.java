package com.example.jais.chessboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.HashMap;

/**
 * Created by jais on 5/28/2016.
 */
public class SquareAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;

    FrameLayout flcp;
    ImageView imgvcp = null;
    HashMap<Integer,Integer> data;
    private int whiteTurn;


    // CHESSBOARD

    private Integer[] chessboardIds = {
            R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare,
            R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare,
            R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare,
            R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare,
            R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare,
            R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare,
            R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare,
            R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare,
            R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare,
            R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare,
            R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare,
            R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare,
            R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare,
            R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare,
            R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare,
            R.drawable.darksquare, R.drawable.lightsquare, R.drawable.darksquare, R.drawable.lightsquare,
    };

    static class ViewHolder {
        public ImageView square;
        public ImageView piece;
    }


    public SquareAdapter(Context c/*, Piece[] listPiece, int turn*/, HashMap<Integer,Integer> dataParameter) {
        mContext = c;
        Context context = c.getApplicationContext();
        mInflater = LayoutInflater.from(context);
        this.data = dataParameter;
    }

    public SquareAdapter(Context c/*, Piece[] listPiece, int turn*/) {
        mContext = c;
        Context context = c.getApplicationContext();
        mInflater = LayoutInflater.from(context);
    }

    public void  setDataParameter(HashMap<Integer,Integer> dataParameter) {
        this.data = dataParameter;
    }

    @Override
    public int getCount() {
        return chessboardIds.length;
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {  // if it's not recycled, initialize some attributes
            rowView = mInflater.inflate(R.layout.square, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.square = (ImageView) rowView.findViewById(R.id.square_background);
            viewHolder.square.setImageResource(chessboardIds[position]);
            viewHolder.piece = (ImageView) rowView.findViewById(R.id.piece);
            rowView.setTag(viewHolder);
            if(data != null) {
                if (data.get(position) != null) {
                    // Add The piece
                    final ImageView pieceView =
                            (ImageView) rowView.findViewById(R.id.piece);
                    pieceView.setImageResource(data.get(position));
                    pieceView.setTag(position);
                }
            }
        }
        return rowView;
    }
}