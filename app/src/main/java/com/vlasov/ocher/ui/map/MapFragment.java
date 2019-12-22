package com.vlasov.ocher.ui.map;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.vlasov.ocher.R;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;

import com.yandex.mapkit.mapview.MapView;

public class MapFragment  extends Fragment {
    private final Point TARGET_LOCATION = new Point(59.945933, 30.320045);

    private MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        /**
         * Задайте API-ключ перед инициализацией MapKitFactory.
         * Рекомендуется устанавливать ключ в методе Application.onCreate,
         * но в данном примере он устанавливается в activity.
         */
        MapKitFactory.setApiKey("7d134546-bc12-4142-9232-4effda046e53");
        /**
         * Инициализация библиотеки для загрузки необходимых нативных библиотек.
         * Рекомендуется инициализировать библиотеку MapKit в методе Activity.onCreate
         * Инициализация в методе Application.onCreate может привести к лишним вызовам и увеличенному использованию батареи.
         */
        // Создание MapView.
        MapKitFactory.initialize(this);

        setContentView(R.layout.fragment_map);


        super.onCreate(savedInstanceState);
        mapView = mapView.findViewById(R.id.mapview);


        // Перемещение камеры в центр Санкт-Петербурга.
        mapView.getMap().move(
                new CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5),
                null);
    }

    private void setContentView(int fragment_map) {
    }

    @Override
    public void onStop() {
        // Вызов onStop нужно передавать инстансам MapView и MapKit.
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onStart() {
        // Вызов onStart нужно передавать инстансам MapView и MapKit.
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }
}
