<?php

include '../db.php';
 
  // $url=basename($_SERVER['REQUEST_URI']);
//$parts=parse_url($url);
 	//parse_str($parts['query'], $query);
	
	//$p_id=$query['p_id']; // Fetching URL Parameter
	

	$sql = "SELECT
labs.LabName,
labs.LabID,
labs.Type,
labs.Address
FROM
labs";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('LabName'=>$row[0],
'LabID'=>$row[1],
'Type'=>$row[2],
'Address'=>$row[3]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>