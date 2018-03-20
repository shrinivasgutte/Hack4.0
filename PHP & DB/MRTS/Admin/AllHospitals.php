<?php

include '../db.php';
 
  // $url=basename($_SERVER['REQUEST_URI']);
//$parts=parse_url($url);
 	//parse_str($parts['query'], $query);
	
	//$p_id=$query['p_id']; // Fetching URL Parameter
	

	$sql = "SELECT
hospital.HospitalID,
hospital.`Name`,
hospital.City
FROM
hospital";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('HospitalID'=>$row[0],
'H_Name'=>$row[1],
'H_City'=>$row[2]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>