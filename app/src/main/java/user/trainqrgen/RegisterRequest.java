package user.trainqrgen;

import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://20092009.000webhostapp.com//Postcurrentlatlong.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String username,String age, String password,String currentlat, String currentlon, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("age", age);
        params.put("username", username);
        params.put("password", password);
        params.put("currentlat", currentlat);
        params.put("currentlon", currentlon);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
