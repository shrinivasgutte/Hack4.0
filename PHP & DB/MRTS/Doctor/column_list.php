<?php
include '../db.php';
 
   $url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	parse_str($parts['query'], $query);
	
	$t_name=$query['t_name']; // Fetching URL Parameter
	
	
 
	$sql = "SHOW COLUMNS FROM `$t_name`";
	

	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('Str_ColumnNames'=>$row[0]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>