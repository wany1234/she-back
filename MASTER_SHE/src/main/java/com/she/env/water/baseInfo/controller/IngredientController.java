package com.she.env.water.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.env.water.baseInfo.service.IngredientService;
import com.she.env.water.model.Ingredient;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/env/water/baseinfo/ingredient")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 원료 조회
     *
     * @param useYn
     *            사용여부
     * @return 원료 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> getIngredients(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String title = map.containsKey("title") ? map.get("title").toString() : "";
        String eairIngrNm = map.containsKey("eairIngrNm") ? map.get("eairIngrNm").toString() : "";
        List<Ingredient> ingredientList = ingredientService.getIngredients(useYn, plantCd, title, eairIngrNm, defaultParam);

        return ResponseEntity.ok().body(ingredientList);
    }

    /**
     * 원료 상세조회
     *
     * @param ewtrIngrNo
     *            원료번호
     * @return Ingredient 원료
     * @throws Exception
     *             예외
     */
    @GetMapping("/ingredient/{ewtrIngrNo}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable("ewtrIngrNo") int ewtrIngrNo) throws Exception {
        Ingredient ingredient = ingredientService.getIngredient(ewtrIngrNo);
        return ResponseEntity.ok().body(ingredient);
    }

    /**
     * 원료 체크
     *
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    @GetMapping("/checkingre")
    public ResponseEntity<Integer> getIngreCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String ewtrIngrNm = map.containsKey("ewtrIngrNm") ? map.get("ewtrIngrNm").toString() : "";
        int ewtrIngrNo = map.containsKey("ewtrIngrNo") ? Integer.parseInt("".equals(map.get("ewtrIngrNo").toString()) ? "0" : map.get("ewtrIngrNo").toString()) : 0;

        return ResponseEntity.ok().body(ingredientService.getIngreCheck(plantCd, ewtrIngrNm, ewtrIngrNo));
    }

    /**
     * 원료 신규등록
     *
     * @param Ingredient
     *            원료
     * @return ewtrIngrNo 원료번호
     * @throws Exception
     *             예외
     */
    @PostMapping("/ingredient")
    public ResponseEntity<Integer> createIngredient(@RequestBody Ingredient ingredient) throws Exception {
        return ResponseEntity.ok().body(ingredientService.createIngredient(ingredient));
    }

    /**
     * 원료 수정
     *
     * @param Ingredient
     *            원료
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/ingredient")
    public ResponseEntity<Integer> updateIngredient(@RequestBody Ingredient ingredient) throws Exception {
        return ResponseEntity.ok().body(ingredientService.updateIngredient(ingredient));
    }
}
