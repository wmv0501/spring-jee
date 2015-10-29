package com.wmv.poc.singleton;

/**
 * @author wvergara, created on 8/5/15.
 */
public class Temp {
    private static final String VERSION_DYNAMIC_REGEX_PATTERN = "^(x+)\\.(x+)\\.(x+)$";


    public static void main(String[] args) {

        final String cvlNullCheck;


        System.out.println(
                "SELECT  " +
                        "gsr_f.code  as field_name, " +
                        "gsr_f.control_value_list as control_value_list, " +
                        "owens_f.code as xml_field_code, " +
                        "gsr_f.control_type as control_type, " +
                        "case (sum(case ORDER_MODE when 'R' then 0 else 1 end)) when 0 then true else false end  as requiredToAll, " +
                        "group_concat( case ORDER_MODE when 'R' then gsr_pf.product_id else null end) as requiredTo, " +
                        "group_concat(case ORDER_MODE when 'R' then null else gsr_pf.product_id end) as helpfulTo, " +
                        "sum(case ORDER_MODE when 'R' then 1 else 0 end) as requiredToTotal, " +
                        "sum(case ORDER_MODE when 'R' then 0 else 1 end) as helpfulToTotal, " +
                        "gsr_f. notes as note," +
                        "gsr_f.label as label, " +
                        "gsr_f.size as size, " +
                        "gsr_sec.document_level as document_level " +
                        "FROM owens_category owens_c " +
                        "INNER JOIN owens_category_mapping owens_cm on owens_c.id = owens_cm.owens_category_id " +
                        "INNER JOIN gsr_product gsr_p on owens_cm.product_id = gsr_p.id " +
                        "INNER JOIN gsr_product_type gsr_pt  on gsr_pt.id = gsr_p.product_type_id " +
                        "INNER JOIN gsr_product_field gsr_pf on gsr_p.id = gsr_pf.product_id " +
                        "INNER JOIN gsr_field gsr_f on gsr_f.id = gsr_pf.gsr_field_id " +
                        "INNER JOIN gsr_section gsr_sec on gsr_f.section_id = gsr_sec.id " +
                        "INNER JOIN owens_field_mapping owens_fm on owens_fm.GSR_FIELD_ID = gsr_f.id " +
                        "INNER JOIN owens_field owens_f on owens_f.id = owens_fm." + "condensed_field" + " " +
                        "WHERE owens_cm.owens_category_id =:xmlCategoryId  " +
                        "GROUP BY owens_f.code " +
                        "ORDER BY owens_f.code asc");
    }


}

