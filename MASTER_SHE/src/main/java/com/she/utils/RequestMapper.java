package com.she.utils;

import java.lang.reflect.Type;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;

public class RequestMapper {

    public RequestMapper() {
    }

    /**
     * 주의 : GET으로 받아온 query string 처리에만 쓸 것(POST, PUT, PATCH는 VO를 생성하여 활용하길 권장)
     * Web에서 받아온 query string을 Hashmap 으로 받아 HashMap<String, Object>로 다시 변환 하는
     * Method 이유는 배열형식의 query string 처리 때문인데(array[0]=1&array[1]=2) 이런 형식으로 들어오기
     * 때문에 제대로 파라미터를 전달 받지 못하는 경우가 발생한다. 따라서, 올바른 파라미터 형식으로 변환한다.
     *
     * @param parameter
     * @return HashMap<String, Object>
     * @throws Exception
     */
    public HashMap<String, Object> convertAsParameter(final HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = new HashMap<String, Object>();
        Iterator<String> iterator = parameter.keySet().iterator();
        String prevArrayKey = "";
        List<Object> objects = new ArrayList<Object>();
        String keyData = "";
        while (iterator.hasNext()) {
            keyData = (String) iterator.next();
            String arrayKey = "";

            // 현재 정보가 배열인지 확인
            // 배열일 경우 key 값이 array[0], array[1].... 이런 형식으로 들어옴
            if (keyData.indexOf("[") > 0 && keyData.indexOf("]") > 0) {
                // 1. 배열 표시 문자([n])를 제거한 키 값 추출
                arrayKey = StringUtils.left(keyData, keyData.indexOf("["));

                // 2-1. 최초로 발견된 배열 이거나, 기존 배열과 동일할 경우에는 objects에 추가한다.
                if (prevArrayKey.equals("") || prevArrayKey.equals(arrayKey)) {
                    objects.add(parameter.get(keyData));
                } else {
                    // 2-2. 다른 배열일 경우 기존 object 정보를 map에 추가하고, 새로운 objects를 생성 후
                    // 현재 값을 넣는다.
                    convertedParameter.put(prevArrayKey, objects);

                    objects = new ArrayList<>();
                    objects.add(parameter.get(keyData));
                }

                prevArrayKey = arrayKey;
            } else {
                convertedParameter.put(keyData, parameter.get(keyData).toString());
            }
        }

        // parameter의 마지막 요소가 배열이거나, 마지막으로 발견된 배열이 아직 map에 추가되지 않았다면 추가한다.
        if (!convertedParameter.containsKey(prevArrayKey)) {
            convertedParameter.put(prevArrayKey, objects);
        }
        return convertedParameter;
    }

    /**
     * convertAsParameter 메써드를 통해 받아온 Hashmap의 Attribute인 object를 파라미터로 받아서
     * Object List를 String 배열로 변환하는 함수
     *
     * @param object
     * @return 변환된 String[]
     * @throws Exception
     */
    public String[] convertObjectListAsStringArray(Object object) throws Exception {
        if (object == null) {
            return null;
        } else if (object.toString().equals("")) {
            return null;
        }
        List<Object> objects = (List<Object>) object;
        String[] converted = objects.toArray(new String[0]);
        return converted;
    }

    /**
     * convertAsParameter 메써드를 통해 받아온 Hashmap의 Attribute인 object를 파라미터로 받아서
     * Object List를 int형 배열로 변환하는 함수
     *
     * @param object
     * @return 변환된 int[]
     * @throws Exception
     */
    public int[] convertObjectListAsIntArray(Object object) throws Exception {
        if (object == null) {
            return null;
        } else if (object.toString().equals("")) {
            return null;
        }
        List<Object> objects = (List<Object>) object;

        int[] converted = new int[objects.size()];
        for (int i = 0; i < converted.length; i++) {
            converted[i] = Integer.parseInt(objects.get(i).toString());
        }
        return converted;
    }

    // public String convertRequestParameterToQueryString(Map<String, String []>
    // requestParam) {
    // Set set = requestParam.entrySet();
    // Iterator it = set.iterator();
    // String queryString = "";
    // while (it.hasNext()) {
    // Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>)
    // it.next();
    // String paramName = entry.getKey();
    // String[] paramValues = entry.getValue();
    // queryString += '&' + paramName;
    // if (paramValues.length == 1) {
    // String paramValue = paramValues[0];
    // if (paramValue.length() > 0)
    // queryString += '=' + paramValue;
    // } else {
    // for (int i = 0; i < paramValues.length; i++) {
    // queryString += String.format("[%d]=%s", i, paramValues[i]);
    // }
    // }
    // }
    // return queryString;
    // }

    public String convertRequestParameterToQueryString(HttpServletRequest request) {
        String queryString = "requestParam:";
        Enumeration enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String paramName = (String) enums.nextElement();
            String[] parameters = request.getParameterValues(paramName);
            queryString += '&' + paramName;
            // Parameter가 배열일 경우
            if (parameters.length > 1) {
                // parameterMap.put(paramName, parameters);
                for (int i = 0; i < parameters.length; i++) {
                    queryString += String.format("[%d]=%s", i, parameters[i]);
                }
                // Parameter가 배열이 아닌 경우
            } else {
                queryString += '=' + parameters[0];
                // parameterMap.put(paramName, parameters[0]);
            }
        }

        return queryString;
    }


    /**
     * JSON 형태의 String을 Class 형태로 반환
     *
     * @param Class<T>,
     *            jsonString
     * @return List<Class>
     * @throws Exception
     */
    public static <T> T convertJSONStringToClass(Class<T> classOfT, String jsonString) throws Exception {
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(jsonString);

        Gson gson = new Gson();
        T t = gson.fromJson(object.toString(), (Type) classOfT);

        return t;
    }

    /**
     * JSON 형태의 String을 collection Map 형태로 반환
     *
     * @param Class<T>,
     *            jsonString
     * @return List<Class>
     * @throws Exception
     */
    public static Map<String, Object> convertJSONStringToCollectionMap(String jsonString) throws Exception {
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(jsonString);

        Gson gson = new Gson();
        Map<String, Object> t = gson.fromJson(object.toString(), Map.class);

        return t;
    }

    public static Map<String, Object> checkMap(Map<String, Object> target) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        for (Map.Entry<String, Object> entry : target.entrySet()) {

            System.out.println("key : " + entry.getKey() + " , value : " + entry.getValue());
            Object targetValue = entry.getValue();
            if ("null".equals(String.valueOf(targetValue))) {
                targetValue = "";
            }
            resultMap.put(entry.getKey(), targetValue);

        }
        return resultMap;
    }

    /**
     * JSON 형태의 String을 List<Class>형태로 반환
     *
     * @param Class<T>,
     *            listString
     * @return List<Class>
     * @throws Exception
     */
    public static <T> List<T> convertJSONStringToList(Class<T> classOfT, String listString) throws Exception {
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = (JsonArray) parser.parse(listString);

        List<T> list = new ArrayList<T>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject object = (JsonObject) jsonArray.get(i);
            Gson gson = new Gson();
            T t = gson.fromJson(object.toString(), (Type) classOfT);
            list.add(t);
        }
        return list;
    }
}
