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
                    <?php require_once './balanco.php'; ?>
                </div>
                <h3>Lançamento</h3>
                <form class="form-inline" method="post" >
                    <div class="control-group">
                        <input type="hidden" name="id" id="id" value="<?php echo $lancamento->get_id(); ?>" />
                        <input class="input-block-level" type="text" id="descricao" name="descricao" placeholder="Descrição" value="<?php echo $lancamento->get_descricao(); ?>" />
                        <input class="span1" type="number" step="0.01" id="quantidade" name="quantidade" placeholder="Qtd" value="<?php echo $lancamento->get_quantidade(); ?>"  />
                        <input class="span2" type="number" step="0.01" id="valor" name="valor" placeholder="Valor" value="<?php echo $lancamento->get_valor(); ?>" />
                        <input  type="text" id="inclusao" name="inclusao" placeholder="Data" value="<?php echo $lancamento->get_inclusao(); ?>"  />
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
                            <label class="control-label" for="categoria">Categorias
                                <?php
                                $select = new dropdownlist();
                                $select->set_collection($categorias);
                                $select->set_name('categoria[]');
                                $select->set_id('categoria');
                                $select->set_select_class('');
                                $select->set_multiple('multiple');
                                $select->set_selected_values($controle->get_categorias($lancamento));
                                $select->render();
                                ?>
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
                        <button type="button" class="btn" id="btn-lancamentos" >Lançamentos</button>
                        <button type="button" class="btn" id="btn-acao-listar" name="acao_istar">Listar</button>
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
                

		$("#btn-acao-listar").click(function() {
		  if($("#btn-acao-listar").text() == 'Listar'){
		      $("#btn-acao-listar").text("Não listar")
		  } else {
		      $("#btn-acao-listar").text("Listar")
		  }
		}); 
                
                $('#inclusao').change(function() {
                    var vdata = $('#inclusao').val().split('/');
                    if( vdata.length === 3 ) {
                        $('#ano').val(vdata[2]);
                        $('#mes').val(vdata[1]);
                    }
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
				  $('#valor').val(valor); 
				}
			});

            });
        </script>  

    </body>
</html>
