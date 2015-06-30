<?xml version="1.0" encoding="UTF-8"?>
<schema xmlns="http://www.w3.org/2001/XMLSchema" targetNamespace="http://www.owens.com/verifications" xmlns:tns="http://www.owens.com/verifications" elementFormDefault="qualified">

    <element name="owens-order" type="tns:cases-type"></element>

    <complexType name="person-type">
    	<sequence minOccurs="1" maxOccurs="1">
    		<element name="reference-subject-id" type="string"></element>
    		<element name="firstname" type="string"></element>
    		<element name="middlename" type="string"></element>
    		<element name="lastname" type="string"></element>
    		<element name="suffix" type="string"></element>
    		<element name="alternate-name" type="string"></element>
    		<element name="birthdate" type="date"></element>
    		<element name="gender" type="string"></element>
    		<element name="social-security-number" type="string"></element>
    	</sequence>
    </complexType>

    <complexType name="cases-type">
    	<sequence minOccurs="1" maxOccurs="unbounded">
    		<element name="case" type="tns:case-type"></element>
    	</sequence>
    	<attribute ref="tns:version"></attribute>
    </complexType>

    <complexType name="case-type">
    	<sequence minOccurs="1" maxOccurs="1">
     		<element name="subject" type="tns:person-type"></element>
    		<element name="elements" type="tns:elements-type"></element>
    	</sequence>
    </complexType>

    <attribute name="version">
    	<simpleType>
    		<restriction base="string">
    			<maxLength value="4"></maxLength>
    			<minLength value="1"></minLength>
    			<whiteSpace value="collapse"></whiteSpace>
    		</restriction>
    	</simpleType>
    </attribute>

    <complexType name="elements-type">
    	<sequence minOccurs="1" maxOccurs="unbounded">
    		<element name="element" type="tns:element-type"></element>
    	</sequence>
    </complexType>

    <complexType name="element-type">
    	<sequence minOccurs="1" maxOccurs="1">

    		<element name="product-id" type="string"></element>
    		<element name="reference-order-id" type="string"></element>
    		<element name="request-reason" type="string"></element>
    		<element name="owens-username" type="string"></element>
    		<element name="search-details" type="tns:search-details-type"></element>
    		<element name="result-details" type="tns:result-details-type"></element>
    		<element name="payment-details" type="tns:payment-details-type"></element>
    		<element name="attachments" type="tns:attachments-type"></element>
    	</sequence>
    </complexType>

    <complexType name="search-details-type">
    	<sequence>
    		<element name="organization-name" type="string"></element>
    		<element name="campus" type="string"></element>
    		<element name="address-line1" type="string"></element>
    		<element name="address-line2" type="string"></element>
    		<element name="city" type="string"></element>
    		<element name="state-province" type="string"></element>
    		<element name="zip-code" type="string"></element>
    		<element name="country" type="string"></element>
    		<element name="phone" type="string"></element>
    		<element name="fax" type="string"></element>
    		<element name="email" type="string"></element>
    		<element name="website" type="string"></element>
    	</sequence>
    </complexType>

    <complexType name="result-details-type">
    	<sequence>
    	<#list languages as language>
            <element name="${language}" type="string"></element>
        </#list>
    	</sequence>
    </complexType>

    <complexType name="payment-details-type">
    	<sequence>
    		<element name="owens-payment-id" type="string"></element>
    		<element name="owens-payment-type" type="string"></element>
    	</sequence>
    </complexType>

    <complexType name="attachments-type">
    	<sequence>
    		<element name="attachment" type="string"></element>
    	</sequence>
    </complexType>

    <complexType name="attachment-type">
    	<sequence>
    		<element name="description" type="string"></element>
    		<element name="encoded-content" type="string"></element>
    		<element name="checksum" type="string"></element>
    	</sequence>
    </complexType>
</schema>


