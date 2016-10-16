/**
 * Created by Leonardo Simberg on 4/11/2016.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.ls.android.translinkrtti;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ls.android.translinkrtti.CustomView.ShowBusesView;
import com.ls.traffic.translink.rtti.NextBus;
import com.ls.traffic.translink.rtti.RttiService;
import com.ls.traffic.translink.rtti.RttiServiceGenerator;
import com.ls.traffic.translink.rtti.Schedule;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Shows the next buses for a Route and Bus Stop
 *
 */
public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";

    private RttiService mRttiService;
    private Button mBtNext;
    private EditText mEtRouteNo;
    private EditText mEtStopNo;
    private TextView mTvError;
    private ShowBusesView mShowBuses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            String api_key = (String)ai.metaData.get("translink_api_key");
            mRttiService = RttiServiceGenerator.createService(api_key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException("Cannot read TRANSLINK_API_KEY from manifest");
        }

        mEtRouteNo = (EditText) findViewById(R.id.etRouteNo);
        mEtStopNo = (EditText) findViewById(R.id.etStopNo);
        mTvError = (TextView) findViewById(R.id.tvError);
        mShowBuses = (ShowBusesView) findViewById(R.id.sbNextBuses);

        mBtNext = (Button) findViewById(R.id.btNextBus);
        mBtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int stopNo = Integer.parseInt(mEtStopNo.getText().toString());
                int routeNo = Integer.parseInt(mEtRouteNo.getText().toString());
                Call<List<NextBus>> callStatus = mRttiService.loadNextBuses(stopNo, routeNo);
                callStatus.enqueue(nextBusesCallback);
//                 mShowBuses.setData(new String[]{"10:22 am", "10:25 am", "11:33 am", "12:01 pm"} );
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Translink Callbacks
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Callback for loadNextBuses endpoint
     */
    Callback<List<NextBus>> nextBusesCallback = new Callback<List<NextBus>>() {
        @Override
        public void onResponse(Call<List<NextBus>> call, Response<List<NextBus>> response) {
            try {
                if (response.errorBody() != null) {
                    String errText = response.errorBody().string();
                    Log.d(TAG, "Error: " + errText);
                    mTvError.setText("Error: " + errText);
                    mTvError.setVisibility(View.VISIBLE);
                    mShowBuses.setVisibility(View.GONE);
                } else {
                    Log.d(TAG, "Body1: " + response.body());
                    List<NextBus> nextBuses = response.body();
                    if (!nextBuses.isEmpty()) {
                        List<Schedule> schedules = nextBuses.get(0).Schedules;
                        Pair<String, String> data[] = new Pair[schedules.size()];
                        int c = 0;
                        for(Schedule schedule: schedules) {
                            Log.d(TAG, "onResponse: bus: " + schedule);
                            data[c++] = new Pair(Integer.toString(nextBuses.get(0).RouteNo),
                                    schedule.ExpectedLeaveTime.split(" ")[0]);
                        }
                        mShowBuses.setData(data);
                        mTvError.setVisibility(View.GONE);
                        mShowBuses.setVisibility(View.VISIBLE);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<List<NextBus>> call, Throwable throwable) {
            Log.e(TAG,"Error: Failure: " + throwable.getLocalizedMessage());
        }
    };

}
