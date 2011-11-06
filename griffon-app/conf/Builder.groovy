root {
    'groovy.swing.SwingBuilder' {
        controller = ['Threading']
        view = '*'
    }
    'griffon.app.ApplicationBuilder' {
        view = '*'
    }
}
//////
root.'griffon.builder.css.CSSBuilder'.view = '*'
root.'griffon.builder.css.CSSBuilder'.controller = ['CSS']


root.'JxlayerGriffonAddon'.addon=true

root.'MiglayoutGriffonAddon'.addon=true

root.'griffon.builder.trident.TridentBuilder'.view = '*'


root.'griffon.builder.gfx.GfxBuilder'.view = '*'
