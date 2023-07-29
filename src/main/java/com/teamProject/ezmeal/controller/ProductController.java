package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.ProductDto;
import com.teamProject.ezmeal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;


    /*상품 리스트 페이지. 카테고리 코드로 검색해서 상품 리스트를 반환*/
    @GetMapping("/catelist")
    public String productListByCateCd(Model model, String cate_cd, @RequestParam(required = false) String sortkeyword) throws SQLException {
        System.out.println("/catelist 컨트롤러 지나갑니다");
        /*상품목록 표현에 필요한 것 모두 받아오기*/
        HashMap map = productService.getProductListByCateCd(cate_cd, sortkeyword);
        model.addAttribute("prodList",map.get("prodList"));
        model.addAttribute("prodImgMap",map.get("prodImgMap"));
        model.addAttribute("prodOptMap",map.get("prodOptMap"));
        model.addAttribute("reviewAvgMap",map.get("reviewAvgMap"));
        model.addAttribute("reviewCntMap",map.get("reviewCntMap"));
        model.addAttribute("cate_cd",cate_cd);
        System.out.println("prodList: "+map.get("prodList").toString());
        System.out.println("[컨트롤러] sortkeyword: "+sortkeyword);

        return "productcatelist";
    }

    /*상품 리스트 페이지. 카테고리 코드로 검색해서 상품 리스트를 반환*/
    @GetMapping("/headerlist")
    public String headerProductList(String headertyp, Model model) throws SQLException {
        System.out.println("/headerlist 컨트롤러 지나갑니다");
        /*상품목록 표현에 필요한 것 모두 받아오기*/
        /*카테고리랑 다른점 -> 상품 List는 서비스에서 headerTyp따라서 알아서 찾아줌 신상품/베스트(리뷰많음)/옵션06만*/
        /*그래서 전체 다 쓰이게 대표이미지 Map으로 다 가져오고 리뷰평균, 리뷰수도 cate_cd 검색없이 사용가능한 조건으로 전부다 가져와*/
        HashMap map = productService.getProductSetForHeader(headertyp);
        model.addAttribute("prodList",map.get("prodList"));
        model.addAttribute("prodImgMap",map.get("prodImgMap"));
        model.addAttribute("prodOptMap",map.get("prodOptMap"));
        model.addAttribute("reviewAvgMap",map.get("reviewAvgMap"));
        model.addAttribute("reviewCntMap",map.get("reviewCntMap"));
        model.addAttribute("headerTyp",headertyp);
        System.out.println("[컨트롤러] headerTyp: "+headertyp);

        return "productcatelist_header";
    }


    @ResponseBody
    @PostMapping("/restcatelist")
    public ResponseEntity<Map<String, Object>> productListByCateCd(@RequestBody String cate_cd, @RequestParam(required = false) String sortkeyword) throws SQLException {
        /*상품목록에 필요한 것 모두 받아오기*/
        HashMap map = productService.getProductListByCateCd(cate_cd, sortkeyword);
        System.out.println("restcatelist 컨트롤러 지나갑니다");
        System.out.println("prodList: "+map.get("prodList").toString());
        System.out.println("[컨트롤러] sortkeyword: "+sortkeyword);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }




    /* 이부분 다 서비스에서 갖고오도록 1메서드로 가져오기 (서비스-트랜잭션)  맵으로 보내주면 꺼내서 모델에 넣어주기 */
    /*컨트롤러에서 중요한 것. 유효성 검사. 다른 페이지로 잘 넘겨주는 것(페이지 이동). 간단하게. 흐름보이게. 예외처리. */
    /*상품 상세 페이지. 나중에 모델에 상품코드관련 후기, 문의 전달해야함.*/
    @GetMapping("/detail")
    public String productDetailView(Model model, Long prod_cd, String cate_cd,  RedirectAttributes redirectAttributes) throws SQLException {

        /*상품 상세페이지에 필요한 것 모두 받아오기*/
        HashMap map = productService.getOneProductByProdCd(prod_cd);

        /*품절된 상품인 경우*/
//        if(map.get("product")==null){
//            redirectAttributes.addAttribute("msg","품절된 상품입니다.");
//            return "redirect:/product/catelist?cate_cd="+cate_cd ;
//        }

        model.addAttribute("product",map.get("product"));
        model.addAttribute("optList",map.get("optList"));
        model.addAttribute("imgList",map.get("imgList"));
        model.addAttribute("reivewAvg",map.get("reivewAvg"));
        model.addAttribute("reviewCount",map.get("reviewCount"));
        model.addAttribute("reviewList",map.get("reviewList"));
//        /*상품문의 추가하기*/
//        model.addAttribute("상품문의",map.get("상품문의"));

        return "productdetail";

    }
    /*서비스에서 묶어 오기 중간에 에러났을 때 대처는 서비스에서  ->  묶어도 3개로 됨. 고민해보기 값 없을 떄*/
    /*DB가 꺼진다면,,? select도 안됨.  db연결 직접 끊기..ㅋㅋ  */











        /*-------------------------------  관리자용 상품 등록.수정.읽기.삭제 시작  ------------------------------------------*/

//    /*관리자 상품 CRUD page - READ */
//    @GetMapping("/regist/read")
//    public String productRegistPage(Model model, Long prod_cd) throws SQLException, JsonProcessingException {
//        /*관리자용 상품 페이지(읽기)에 필요한 것 모두 받아오기*/
//        HashMap map = productService.getOneProductByProdCdForMng(prod_cd);
//
//        /*모델에 담기*/
//        model.addAttribute("product", map.get("product"));
//        model.addAttribute("optList", map.get("optList"));
//        model.addAttribute("imgList", map.get("imgList"));
//        model.addAttribute("dcList", map.get("dcList"));
//        model.addAttribute("cateList", map.get("cateList"));
//        model.addAttribute("custList", map.get("custList"));
//        model.addAttribute("stusList", map.get("stusList"));
//        model.addAttribute("mode","READ");
//
//        return "productRegistration";
//
//    }


//    /*관리자 상품 CRUD page - WRITE 새상품 등록 페이지 */
//    @GetMapping("/regist/write")
//    public String productMngRegistWritePage(Model model) throws SQLException {
//
//        /*관리자용 상품 페이지(읽기)에 필요한 것 모두 받아오기*/
//        HashMap map = productService.getListForProductRegist();
//
//        /*모델에 담기*/
//        model.addAttribute("dcList", map.get("dcList"));
//        model.addAttribute("cateList", map.get("cateList"));
//        model.addAttribute("custList", map.get("custList"));
//        model.addAttribute("stusList", map.get("stusList"));
//        model.addAttribute("mode","WRITE");
//
//        return "productRegistration";
//    }
//    /*------------------------------*/
//
//    @PostMapping("/regist/write")
//    public ResponseEntity<?> registerProduct(@RequestBody ProductRegistrationRequest request) throws SQLException {
//        // 이제 request 안에는 ProductDto 객체와 ProductOptionDto 객체 리스트 있음
//        ProductDto productDto = request.getProductDto();
//        List<ProductOptionDto> productOptionDtos = request.getProductOptionDto();
//
//        for(ProductOptionDto optDto : productOptionDtos){
//            System.out.println("optDto: "+optDto.toString());
//        }
//
//        Map<String,Integer> registResult = productService.prodAndOptionRegist(productDto, productOptionDtos);
//
//        // 처리가 성공적으로 끝나면, 응답을 클라이언트에 보냅니다.
//        return ResponseEntity.ok(registResult);
//    }




//
//
//    /*관리자 상품 CRUD page - WRITE */
//    @PostMapping("/regist/write")
//    public void productMngRegistWritePostPage(ProductDto productDto, ProductOptionDto[] productOptionDto) throws SQLException {
//
//        System.out.println("productDto: "+productDto);
//        System.out.println();
//        System.out.println("optDto.length: "+productOptionDto.length);
//    }
//    /*------------------------------*/




    /*관리자 상품 CRUD page - UPDATE */ /*수정 시작하려는 화면!  업데이트문 아직*/
    @GetMapping("/regist/modify")
    public String productMngRegistModifyPage(Long prod_cd, Model model) throws SQLException {
//        ProductDto productDto = productService.getProductByProdCd(prod_cd);
        /*모델에 담기*/
//        model.addAttribute("productDto", productDto);
        model.addAttribute("mode", "UPDATE_MODE");

        return "productRegistration";
    }

    /*------------------------------*/
    /*관리자 상품 CRUD page - UPDATE */ /*수정 다하고 객체가 넘어왔음. 업데이트문 아직*/
    @PostMapping("/regist/modify")
    public String productMngRegistUpdatePage(ProductDto productDto, Model model) throws SQLException {

        /*서비스에서 업데이트 하는 메서드 사용하겠지?
        * 업데이트 성공하면 성공 로우수 1이니까
        * 반환값 1일 때 로직 작성하자
        * 반환값 1이 아닐때는 예외처리 해야해*/
        /*모델에 담기*/


        return "productRegistration";
    }
    /*------------------------------*/


    /*관리자 상품 CRUD page - DELETE */


    @PostMapping("/testProd")
    public String testProd(@RequestBody ProductDto productDto, Model model) throws SQLException {
        /*테스트용이라서 prod_cd 안줌*/
        model.addAttribute("productDto",productDto);

        return "testProd";
    }


    /*-------------------------------  관리자용 상품 관리 페이지 시작  ------------------------------------------*/

    @GetMapping("/mng/productlist")
    public String productMngListPage(Model model, String cate_cd) throws SQLException {

        /*해당 상품코드의 상품객체 1개 전달*/  /*원래는 상품 List인데 지금 테스트중이라 하나만*/  /*매개변수 String prod_cd*/
//        ProductDto productDto = productService.searchProdCd(prod_cd);

        /*카테고리 코드로 검색해서 상품 리스트를 반환*/
//        List productCateCdList = productService.getProductListByCateCd(cate_cd);

        /*나중에는 이미지도 전달해야겠지...*/

        /*모델에 담기*/
//        model.addAttribute("productDto", productDto);
//        model.addAttribute("productCateCdList", productCateCdList);


        return "productMngList";

    }


    @GetMapping("/firststep")
    public String makeCategoryTest() throws SQLException {

        return "productcatelist_test";
    }


    @GetMapping("/make_cate_list")
    public HashMap makeCategoryTest(String cate_cd, @RequestParam(required = false) String sortkeyword) throws SQLException {

        /*상품목록 표현에 필요한 것 모두 받아오기*/
        HashMap map = productService.getProductListByCateCd(cate_cd, sortkeyword);
//        model.addAttribute("prodList",map.get("prodList"));
//        model.addAttribute("prodImgList",map.get("prodImgList"));
//        model.addAttribute("prodOptMap",map.get("prodOptMap"));
//        model.addAttribute("reviewAngMap",map.get("reviewAngMap"));
//        model.addAttribute("reviewCntMap",map.get("reviewCntMap"));
//        model.addAttribute("cate_cd",cate_cd);
        System.out.println("비동기로 리스트 만들기!");

        return map;
    }








}