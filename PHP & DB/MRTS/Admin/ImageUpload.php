<?php

 $url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	parse_str($parts['query'], $query);
	
	$nfc_id=$query['nfc_id']; // Fetching URL Parameter
	


$target_path = "../User/$nfc_id/";

echo "uploading started". $row["ID"];

$target_path = $target_path . basename( $_FILES['uploadedfile']['name']);

if(move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $target_path)) {
    echo "The file ".  basename( $_FILES['uploadedfile']['name'])." has been uploaded";
    chmod ("../User/$nfc_id/".basename( $_FILES['uploadedfile']['name']), 0644);
} else{
    echo "There was an error uploading the file, please try again!";
    echo "filename: " .  basename( $_FILES['uploadedfile']['name']);
    echo "target_path: " .$target_path;
}