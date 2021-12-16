function textValidate(txtBox, maxLength, opt) { var regex = "";
	if (opt == 'SI') { regex = /[A-Za-z]/;
	} else if (opt == 'OI') { regex = /[A-Za-z`~!#$%^@.&*()+='"?,<>{}\/-]/;
	} else if (opt == 'MN') { regex = /[A-Za-z`~!#$%^@.&*()-+='"?<>{}]/;
	} else if (opt == 'PG') { regex = /[A-Za-z`~!#$%^@&*()-+='"?,<>{}]/;
	} else if (opt == 'AI') { regex = /[@#-,.`~!$%^&*|:;\_+='"?\\<>{}\[\/\]]/;
	} else if (opt == 'OA') { regex = /[0-9`~!@#$,%^&*()_:\-+={}|\\{}\[\];'\"?><\/]/;
	} else if (opt == 'SA') { regex = /[0-9`~!#$%^&*()+=\_\'\[\]\\;,{}|\":<>?]/;
	} else if (opt == 'SR') { regex = /[0-9`~!#$%^&*()+=\_\'\[\]\\;,{}|\":<>?]/;
	} else if (opt == 'NV') { regex = /[~]/;
	} else if (opt == 'EML') { regex = /[`~!#$%^&*():\-+={}|\\,{}\[\];'\"?><\/]/;
	} else if (opt == 'PSD') { regex = /[`~&*|:;\_+='"?\\<>{}\[\/\]]/;
	} else if (opt == 'DT') { regex = /[A-Za-z`~!@#$%^&*()_:\ +={}|\\{}\[\];'\"?><\/]/;
	} else if (opt == 'JbTit') { regex = /[`~^|\\\{}\[\/\]]/;
	} else if (opt == 'MNM') { regex = /[A-Za-z`~!@#$%^&*()_:\ \-+={}|\\{}\[\];'\"?><\/]/;
	} else if (opt == 'PHNM') { regex = /[A-Za-z`~!@#$%^&*()_:\ \{}|\\{.}\[\];'\"?><\/]/;
	} else if (opt == 'NM') { regex = /[A-Za-z`~!@#$,%^&*()_:\ +={}|\\{}\[\];'\"?><\/]/;
	} else if (opt == 'DOC') { regex = /[`~!$%^&*|:;\+='"?\\<>{}\[\/\]]/;
	} else if (opt == 'QUES') { regex = /[`~!$%^&*|:;\_+="\\<>{}\[\/\]]/;
	} else if (opt == 'RANGE') { regex = /[A-Za-z`~!@#$%^&*()_:\ \+={}|\\{}\[\];'\"?><\/]/;
	} else if (opt == 'ONLYALPHABETS') { regex = /[0-9@#-,.`~!$%^&*|:;\_+='"?\\<>{}\[\/\]]/;
	} else if (opt == 'ADP') { regex = /[`~']/;
	} else if (opt == 'TK') { regex = /[`~!$%^*|:;\_+='"?\\<>{}\[\/\]]/;
	} else if (opt == 'DWOLLA') { regex = /[A-Za-z`~!@#$%^&*()_:\ \{}|\\{}\[\];'\"?><\/]/;
	} else if (opt == 'AN') { regex = /[`~@#&.,!-()$%^*|:;\_+='"?\\<>{}\[\/\]]/;
	} else if (opt == 'ECHO') { regex = /[A-Za-z`~!@#$,%^&*()_:\+={}|\\{}\[\];'\"?><\/]/;
	} else if (opt == 'NU1-9') { regex = /[0A-Za-z`~!#$%^@.&*()+='"?,<>{}\/-]/;
	} else if (opt == 'REGNM') { regex = /[@#-,.`~!$%^&*|:;\_+='"?\<>{}\[\]]/;}
	if (regex.test(txtBox.value)) { txtBox.value = txtBox.value.substring(0, (txtBox.value.length - 1)); textValidate(txtBox, maxLength, opt);
	} else { if (txtBox.value.length > maxLength) { txtBox.value = txtBox.value.substring(0, maxLength); textValidate(txtBox, maxLength, opt);}
	}
}
function validateEmail(sEmail) {var filter = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;if (filter.test(sEmail)) {return true;} else {return false;} }