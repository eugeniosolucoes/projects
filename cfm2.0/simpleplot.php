<?php

# PHPlot Example - Horizontal Bars
require_once 'phplot.php';

$fonts_path = '/usr/share/wine/fonts';

$data = array(
array('bebidas',326.0),
array("combustível",346.44),
array("compras de mercado", 747.79),
array("despesas do lar", 865.02),
array("moradia", 1557.91),
array("Padaria", 105.62),
array("serviços essenciais", 335.44),
array("tarifas bancárias", 28.90),
array("transporte", 520.0),
array("vitor hugo", 850.44)

);

$plot = new PHPlot(800, 400);
$plot->SetTTFPath($fonts_path);
$elements = array('legend', 'generic', 'x_label', 'y_label', 'x_title',  'y_title');
$plot->SetFontTTF("title", 'tahoma.ttf', 12);
foreach ($elements as $element) {
    $plot->SetFontTTF($element, 'tahoma.ttf', 10);
}
$plot->SetImageBorderType('plain'); // Improves presentation in the manual
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
$plot->SetDrawXGrid(FALSE);
#  Set the bar fill color:
$plot->SetDataColors('blue');
#  Use less 3D shading on the bars:
$plot->SetShading(2);
$plot->SetDataValues($data);
$plot->SetDataType('text-data-yx');
$plot->SetPlotType('bars');
$plot->DrawGraph();