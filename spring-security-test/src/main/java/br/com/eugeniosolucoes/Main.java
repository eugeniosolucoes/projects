package br.com.eugeniosolucoes;

import br.com.eugeniosolucoes.controller.MainController;
import br.com.eugeniosolucoes.ui.swing.MainDesktop;
import java.util.Locale;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    static ApplicationContext context;

    public static void main( String[] args ) {

        try {
            for ( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels() ) {
                if ( "Nimbus".equals( info.getName() ) ) {
                    javax.swing.UIManager.setLookAndFeel( info.getClassName() );
                    break;
                }
            }
        } catch ( ClassNotFoundException ex ) {
            java.util.logging.Logger.getLogger( MainDesktop.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( InstantiationException ex ) {
            java.util.logging.Logger.getLogger( MainDesktop.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( IllegalAccessException ex ) {
            java.util.logging.Logger.getLogger( MainDesktop.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        } catch ( javax.swing.UnsupportedLookAndFeelException ex ) {
            java.util.logging.Logger.getLogger( MainDesktop.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }


        Locale.setDefault( new Locale( "pt", "BR" ) );

        context = new ClassPathXmlApplicationContext( "META-INF/spring-config.xml" );

        MainController controller = context.getBean( MainController.class );

        controller.execute();

    }
}
