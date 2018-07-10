<?php
global $lib_path;
$lib_path = '../../';

require_once $lib_path . 'config/load.php';

$controle = new lancamento_controle();

$lancamento = $controle->execute();

$categoria_controle = new categoria_controle();

$frequencia_controle = new frequencia_controle();

$frequencias = $frequencia_controle->listar();

$categorias = $categoria_controle->listar_por_tipo($lancamento);
?> 

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>

        <?php include $lib_path . 'inc/cabecalho.php'; ?>

    </head>
    <body>
        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

        <!-- This code is taken from http://twitter.github.com/bootstrap/examples/hero.html -->

        <?php include $lib_path . 'inc/menu-main.php'; ?>

        <div class="container">

            <?php exibir_mensagens(); ?>

            <!-- Main hero unit for a primary marketing message or call to action -->
            <div class="hero-unit">
                <button type="button" class="btn-block" id="btn-balanco" >Balanço</button>
                <div id="div-balanco" class="ui-widget-content ui-corner-all">
                    <?php //require_once './balanco.php'; ?>
                </div>
                <h3>Lançamento</h3>
                <form class="form-inline" method="post" >
                    <div class="control-group">
                        <input type="hidden" name="id" id="id" value="<?php echo $lancamento->get_id(); ?>" />
                        <input class="input-block-level" required type="text" id="descricao" name="descricao" placeholder="Descrição" value="<?php echo $lancamento->get_descricao(); ?>" />
                        <input style="text-align: right;" class="span1" type="number" step="0.001" id="quantidade" name="quantidade" placeholder="Qtd" value="<?php echo $lancamento->get_quantidade(); ?>"  />
                        <input style="text-align: right;" class="span2" type="number" step="0.001" id="valor" name="valor" placeholder="Valor" value="<?php echo $lancamento->get_valor(); ?>" />
                        <input style="text-align: right;" class="span2" id="val_tot" readonly="" value="0.00" />
                        <input  type="text" id="inclusao" name="inclusao" placeholder="Data" value="<?php echo $lancamento->get_inclusao(); ?>"  />
                        <label class="control-label" for="parcelado">Parcelado
                            <input type="checkbox" name="parcelado" id="parcelado" <?php echo $lancamento->parcelado ? 'checked' : ''; ?> />
                        </label>
                        <input style="text-align: right;" class="span1 parcelado" type="number" min="0" step="1" id="qtd_parcelas" name="qtd_parcelas" placeholder="Qtd Parcelas" value="<?php echo $lancamento->qtd_parcelas; ?>"  />
                        <input style="text-align: right;" class="span1 parcelado" readonly="" id="num_parcela" value="<?php echo $lancamento->num_parcela; ?>"  />
                        <input  type="hidden" id="ano" name="ano" value=""  />
                        <input  type="hidden" id="mes" name="mes" value=""  />
                        <div class="control-group">
                            <label class="control-label" for="frequencia">Frequência
                                <?php
                                $select1 = new dropdownlist();
                                $select1->set_collection($frequencias);
                                $select1->set_name('frequencia');
                                $select1->set_id('frequencia');
                                $select1->set_selected_values(array($lancamento->get_frequencia()));
                                $select1->render();
                                ?>
                            </label>
                            <label class="control-label" for="tipo">Tipo
                                <?php
                                printf("<select class='span2' name='tipo' id='tipo' onchange='retornar_categorias_json(this.value%s)' >", $lancamento->get_id() ? ", {$lancamento->get_id()}" : ", -1");
                                $tipos = array('Débito', 'Crédito');
                                foreach ($tipos as $key => $value) {
                                    printf("<option value=\"$key\" %s >$value</option>", $lancamento->get_tipo() == $key ? 'selected' : '');
                                }
                                printf("</select>");
                                ?>
                            </label>
                            <input type="text" id="categoria_busca" name="categoria_busca" placeholder="busca categoria" value="" />
                            <label class="control-label" for="categoria">Categorias
                                <?php /*
                                $select = new dropdownlist();
                                $select->set_collection($categorias);
                                $select->set_name('categoria[]');
                                $select->set_id('categoria');
                                $select->set_select_class('');
                                $select->set_multiple('multiple');
                                $select->set_selected_values($controle->get_categorias($lancamento));
                                $select->render(); */
                                ?>
                                <select name="categoria[]" id="categoria" multiple ></select>
                            </label>
                            <input class="input-block-level" type="text" id="link" name="link" placeholder="Link(URL)" value="<?php echo $lancamento->get_link(); ?>" />
                            <?php 
                                if($lancamento->get_link()){
                                    printf("<a href='%s' target='_blank'><img src='../../img/external-link.png' /></a>", $lancamento->get_link());
                                }
                            ?>
                        </div>
                    </div>
                    <div class="control-group">
                        <button type="submit" class="btn" name="comando" value="incluir">Incluir novo</button>
                        <?php if ($lancamento->get_id()) { ?>
                            <button type="submit" class="btn" name="comando" value="salvar">Salvar</button>
                        <?php } ?>
                        <?php if ($lancamento->get_id()) { ?>
                            <button type="button" class="btn" name="comando" value="excluir" onclick="excluir_lancamento(<?php echo $lancamento->get_id(); ?>)">Excluir</button>
                        <?php } ?>                            
                        <button type="button" class="btn" id="btn-lancamentos" >Lançamentos</button>
                        <input type="checkbox" id="btn-acao-listar" checked title="Carregar lançamentos" />
                    </div>
                </form>
            </div>
            <hr>
            <footer>
                <p><?php include $lib_path . 'inc/rodape.php'; ?></p>
            </footer>

        </div> <!-- /container -->

        <?php include $lib_path . 'inc/footer_bootstrap.php'; ?>

        <script>
            $(document).ready(function() {
    
                $('#div-balanco').toggle('up');

                $('#btn-balanco').click(function () {
                    $('#div-balanco').toggle('blind');
                });
                
                var vinclusao = $('#inclusao').val().split('/');
                if( vinclusao.length === 3 ) {
                    $('#ano').val(vinclusao[2]);
                    $('#mes').val(vinclusao[1]);
                }
                
                $("#inclusao").datepicker({
                    buttonText: "data do lançamento",
                    showButtonPanel: true,
					changeMonth: true,
					changeYear: true
                });
                
                toggle_qtd_parcelas();
                
                $('#parcelado').click(function() {
                    toggle_qtd_parcelas();
                });
                
                $('#inclusao').change(function() {
                    var vdata = $('#inclusao').val().split('/');
                    if( vdata.length === 3 ) {
                        $('#ano').val(vdata[2]);
                        $('#mes').val(vdata[1]);
                    }
                    load_balanco(vdata[2],vdata[1]);
                });

                $('#btn-lancamentos').click(function(){
                    listar_periodo('<?php echo CONTEXT_PATH; ?>view/lancamento/list.php?comando=listar');
                });

			$('#valor').on('focus',function() {
				var text = $('#descricao').val(); 
				if( text.indexOf("ITAU UNICLASS:") != -1 ) { 
				  var f = text.indexOf("Local:"); 
				  var text1 = text.substr(0, f); 
				  var i = text1.lastIndexOf("R") 
				  var text2 = text1.substr(i+2).trim(); 
				  var valor = text2.replace(',', '.'); 
				  $('#valor').val(valor); 
                                  calc_total();
				}
				if( text.indexOf("UNICLASS MC PLAT") != -1 ) { 
				  var f = text.indexOf("em "); 
				  var text1 = text.substr(0, f); 
				  var i = text1.lastIndexOf("R") 
				  var text2 = text1.substr(i+2).trim(); 
				  var valor = text2.replace(',', '.');
                                  $("#categoria option[value=30]").prop("selected", true);
                                  var now = new Date();
                                  var mes_corrente = now.getMonth() +1;
                                  var proximo_mes = (mes_corrente === 12) ? 1 : mes_corrente + 1; 
                                  var ano = (proximo_mes === 1) ? now.getFullYear() + 1 : now.getFullYear();
                                  var strData = proximo_mes < 10 ? '11/0'+(proximo_mes)+'/'+ano : '11/'+(proximo_mes)+'/'+ano;
                                  $('#inclusao').val(strData);
                                  $('#inclusao').change();
				  $('#valor').val(valor);
                                  calc_total();
				}
			});
                        load_balanco(<?php echo substr($lancamento->get_inclusao(), 6); ?>, <?php echo substr($lancamento->get_inclusao(), 3, 2); ?>);
                        $('#valor').on('change', function(){
                            calc_total();
                        });
                        $('#quantidade').on('change', function(){
                            calc_total();
                        });
                        calc_total();
                        
                        retornar_categorias_json(<?php echo ($lancamento->tipo ? 1 : 0). ", ". ($lancamento->get_id() ? $lancamento->get_id() : -1); ?>);
                            
                        $('#categoria_busca').on('blur', function(){
                            var txt = $('#categoria_busca').val();
                            $('#categoria option').filter(function() { 
                                return ($(this).text() == txt); 
                            }).prop('selected', true);

                        });    
                            
            });
            function calc_total(){
                var v = $('#valor').val();
                var q = $('#quantidade').val();
                if(!Number.isNaN(v) && !Number.isNaN(q)){
                    var vt = v * q;
                    $('#val_tot').val(vt.toFixed(2));
                }    
            }    
            function load_balanco(ano, mes) {
                $('#div-balanco').empty();
                $('#div-balanco').html("<div style='width: 100; text-align: center;'><img src='<?php echo CONTEXT_PATH . "img/loading.gif' />Carregando Balanço...</div>"; ?>");
                $.ajax({
                       url: 'balanco.php',
                       data: {
                            comando: 'retornar',
                            ano: ano,
                            mes: mes
                        }
                   }).done(function (resposta) {
                       $('#div-balanco').html(resposta);
                });
            }
            
            function excluir_lancamento(id){
                var resultado = confirm('Deseja realmente EXCLUIR este lançamento?');
                if (resultado) {
                    var vdata = $('#inclusao').val().split('/');
                    var params = new Array();
                    params['comando'] = 'excluir';
                    params['lancamentos'] =  [id];
                    params['mes'] = vdata[1];
                    params['ano'] = vdata[2];
                    post_to_url('list.php', params, 'post');
                }
                return resultado;
            }
            
            function toggle_qtd_parcelas(){
                if($('#parcelado').is(':checked')){
                    $('#qtd_parcelas').attr('min', 2);
                    $('#qtd_parcelas').show();
                    $('#num_parcela').show();
                } else {
                    $('#qtd_parcelas').attr('min', 0);
                    $('#qtd_parcelas').hide();
                    $('#num_parcela').hide();
                }
            }
        </script>  

    </body>
</html>
