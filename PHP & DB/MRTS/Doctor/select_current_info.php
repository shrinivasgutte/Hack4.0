<?php
include '../db.php';
 
   $url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	parse_str($parts['query'], $query);
	
	$Col_name=$query['Col_name']; // Fetching URL Parameter
	$T_name=$query['t_name']; // Fetching URL Parameter
	$W_colName=$query['W_colName']; // Fetching URL Parameter
	$p_id=$query['p_id']; // Fetching URL Parameter
	
	
 
	$sql = "SELECT
`$Col_name`
FROM
`$T_name`
where `$W_colName`='$p_id'";
	

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