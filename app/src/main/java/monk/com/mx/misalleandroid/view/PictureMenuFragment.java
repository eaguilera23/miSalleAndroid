package monk.com.mx.misalleandroid.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import monk.com.mx.misalleandroid.R;

/**
 * Created by edago on 7/21/17.
 */
public class PictureMenuFragment extends BottomSheetDialogFragment {

    Activity mainActivity;
    LinearLayout _from_camera, _from_gallery;
    private final int PICTURE_TAKEN_FROM_CAMERA = 1;
    private final int PICTURE_TAKEN_FROM_GALLERY = 2;

    public PictureMenuFragment(){
    }

    public void setMainActivity(Activity activity){
        mainActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.btm_sheet_picker, container);

        _from_camera = (LinearLayout)view.findViewById(R.id.lly_from_camera);
        _from_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(mainActivity.getPackageManager()) != null) {
                    getActivity().startActivityForResult(takePictureIntent, PICTURE_TAKEN_FROM_CAMERA);
                    // https://developer.android.com/training/camera/photobasics.html
                    dismiss();
                }
            }
        });

        _from_gallery = (LinearLayout)view.findViewById(R.id.lly_from_gallery);
        _from_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent (Intent.ACTION_PICK, null);
                galleryIntent.setType("image/*");
                mainActivity.startActivityForResult(galleryIntent, PICTURE_TAKEN_FROM_GALLERY);
                dismiss();
            }
        });

        return view;
    }
}
