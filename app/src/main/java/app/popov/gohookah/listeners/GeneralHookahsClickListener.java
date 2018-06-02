package app.popov.gohookah.listeners;

import android.content.Context;
import android.gesture.Gesture;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

public class GeneralHookahsClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        public void onItemClick(View view, int position);

        public void onLongItemClick(View view, int position);
    }

    GestureDetector gestureDetector;

    public GeneralHookahsClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener clickListener){
        this.listener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent){
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent){
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (child != null && listener != null){
                    listener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }
    @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && listener != null && gestureDetector.onTouchEvent(e)) {
            listener.onItemClick(childView, view.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

    @Override
    public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}
}
