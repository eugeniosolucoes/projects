<?php

# PHPlot Example - Horizontal Bars
require_once 'phplot.php';

if(!isset( $_REQUEST['dados'] )){
    throw new Exception("dados inválidos!");
}
$data = json_decode($_REQUEST['dados']);

usort($data, 'my_compare');

function my_compare($obj1, $obj2){
    if ($obj1[0] > $obj2[0]) {
        return -1;
    } elseif ($obj1[0] < $obj2[0]) {
        return 1;
    }
    return 0;
}

$plot = new PHPlot(900, 400);

$plot->SetUseTTF(true);

$plot->SetFontTTF('generic', '', 10);
$plot->SetFontTTF('title', '', 12);
$plot->SetFontTTF('legend', '', 10);
$plot->SetFontTTF('x_label', '', 10);
$plot->SetFontTTF('y_label', '', 10);
$plot->SetFontTTF('x_title', '', 10);
$plot->SetFontTTF('y_title', '', 10);

$plot->SetImageBorderType('none'); // Improves presentation in the manual
$plot->SetTitle("Lançamentos por Categoria");
$plot->SetBackgroundColor('white');
#  Set a tiled background image:
//$plot->SetPlotAreaBgImage('images/drop.png', 'centeredtile');
#  Force the X axis range to start at 0:
$plot->SetPlotAreaWorld(0);
#  No ticks along Y axis, just bar labels:
$plot->SetYTickPos('none');
#  No ticks along X axis:
$plot->SetXTickPos('none');
#  No X axis labels. The data values labels are sufficient.
$plot->SetXTickLabelPos('none');
#  Turn on the data value labels:
$plot->SetXDataLabelPos('plotin');
#  No grid lines are needed:
$plot->SetDrawXGrid(TRUE);
#  Set the bar fill color:
$plot->SetDataColors('blue');
#  Use less 3D shading on the bars:
$plot->SetShading(10);
$plot->SetDataValues($data);
$plot->SetDataType('text-data-yx');
$plot->SetPlotType('bars');
$plot->DrawGraph();
