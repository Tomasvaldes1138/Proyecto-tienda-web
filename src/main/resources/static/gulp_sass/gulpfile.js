const { src, dest, watch , parallel } = require('gulp');
const sass =  require('gulp-sass')(require('sass'));

const paths = {
    scss: 'src/scss/**/*.scss',
}

function css() {
    return src(paths.scss)
        .pipe(sass())
        .pipe( dest('./build/css') );
}


function watchArchivos() {
    watch( paths.scss, css );
}
  
exports.css = css;
exports.watchArchivos = watchArchivos;
exports.default = parallel(css, watchArchivos ); 