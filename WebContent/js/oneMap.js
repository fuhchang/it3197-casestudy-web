/*!
 * jQuery JavaScript Library v1.3.2
 * http://jquery.com/
 *
 * Copyright (c) 2009 John Resig
 * Dual licensed under the MIT and GPL licenses.
 * http://docs.jquery.com/License
 *
 * Date: 2009-02-19 17:34:21 -0500 (Thu, 19 Feb 2009)
 * Revision: 6246
 */
//add index on convert coordinate, add themename in getmashupdata 
//change global one map token and css + script src 
(function(){

var 
	// Will speed up references to window, and allows munging its name.
	window = this,
	// Will speed up references to undefined, and allows munging its name.
	undefined,
	// Map over jQuery in case of overwrite
	_jQuery = window.jQuery,
	// Map over the $ in case of overwrite
	_$ = window.$,

	jQuery = window.jQuery = window.$ = function( selector, context ) {
		// The jQuery object is actually just the init constructor 'enhanced'
		return new jQuery.fn.init( selector, context );
	},

	// A simple way to check for HTML strings or ID strings
	// (both of which we optimize for)
	quickExpr = /^[^<]*(<(.|\s)+>)[^>]*$|^#([\w-]+)$/,
	// Is it a simple selector
	isSimple = /^.[^:#\[\.,]*$/;

jQuery.fn = jQuery.prototype = {
	init: function( selector, context ) {
		// Make sure that a selection was provided
		selector = selector || document;

		// Handle $(DOMElement)
		if ( selector.nodeType ) {
			this[0] = selector;
			this.length = 1;
			this.context = selector;
			return this;
		}
		// Handle HTML strings
		if ( typeof selector === "string" ) {
			// Are we dealing with HTML string or an ID?
			var match = quickExpr.exec( selector );

			// Verify a match, and that no context was specified for #id
			if ( match && (match[1] || !context) ) {

				// HANDLE: $(html) -> $(array)
				if ( match[1] )
					selector = jQuery.clean( [ match[1] ], context );

				// HANDLE: $("#id")
				else {
					var elem = document.getElementById( match[3] );

					// Handle the case where IE and Opera return items
					// by name instead of ID
					if ( elem && elem.id != match[3] )
						return jQuery().find( selector );

					// Otherwise, we inject the element directly into the jQuery object
					var ret = jQuery( elem || [] );
					ret.context = document;
					ret.selector = selector;
					return ret;
				}

			// HANDLE: $(expr, [context])
			// (which is just equivalent to: $(content).find(expr)
			} else
				return jQuery( context ).find( selector );

		// HANDLE: $(function)
		// Shortcut for document ready
		} else if ( jQuery.isFunction( selector ) )
			return jQuery( document ).ready( selector );

		// Make sure that old selector state is passed along
		if ( selector.selector && selector.context ) {
			this.selector = selector.selector;
			this.context = selector.context;
		}

		return this.setArray(jQuery.isArray( selector ) ?
			selector :
			jQuery.makeArray(selector));
	},

	// Start with an empty selector
	selector: "",

	// The current version of jQuery being used
	jquery: "1.3.2",

	// The number of elements contained in the matched element set
	size: function() {
		return this.length;
	},

	// Get the Nth element in the matched element set OR
	// Get the whole matched element set as a clean array
	get: function( num ) {
		return num === undefined ?

			// Return a 'clean' array
			Array.prototype.slice.call( this ) :

			// Return just the object
			this[ num ];
	},

	// Take an array of elements and push it onto the stack
	// (returning the new matched element set)
	pushStack: function( elems, name, selector ) {
		// Build a new jQuery matched element set
		var ret = jQuery( elems );

		// Add the old object onto the stack (as a reference)
		ret.prevObject = this;

		ret.context = this.context;

		if ( name === "find" )
			ret.selector = this.selector + (this.selector ? " " : "") + selector;
		else if ( name )
			ret.selector = this.selector + "." + name + "(" + selector + ")";

		// Return the newly-formed element set
		return ret;
	},

	// Force the current matched set of elements to become
	// the specified array of elements (destroying the stack in the process)
	// You should use pushStack() in order to do this, but maintain the stack
	setArray: function( elems ) {
		// Resetting the length to 0, then using the native Array push
		// is a super-fast way to populate an object with array-like properties
		this.length = 0;
		Array.prototype.push.apply( this, elems );

		return this;
	},

	// Execute a callback for every element in the matched set.
	// (You can seed the arguments with an array of args, but this is
	// only used internally.)
	each: function( callback, args ) {
		return jQuery.each( this, callback, args );
	},

	// Determine the position of an element within
	// the matched set of elements
	index: function( elem ) {
		// Locate the position of the desired element
		return jQuery.inArray(
			// If it receives a jQuery object, the first element is used
			elem && elem.jquery ? elem[0] : elem
		, this );
	},

	attr: function( name, value, type ) {
		var options = name;

		// Look for the case where we're accessing a style value
		if ( typeof name === "string" )
			if ( value === undefined )
				return this[0] && jQuery[ type || "attr" ]( this[0], name );

			else {
				options = {};
				options[ name ] = value;
			}

		// Check to see if we're setting style values
		return this.each(function(i){
			// Set all the styles
			for ( name in options )
				jQuery.attr(
					type ?
						this.style :
						this,
					name, jQuery.prop( this, options[ name ], type, i, name )
				);
		});
	},

	css: function( key, value ) {
		// ignore negative width and height values
		if ( (key == 'width' || key == 'height') && parseFloat(value) < 0 )
			value = undefined;
		return this.attr( key, value, "curCSS" );
	},

	text: function( text ) {
		if ( typeof text !== "object" && text != null )
			return this.empty().append( (this[0] && this[0].ownerDocument || document).createTextNode( text ) );

		var ret = "";

		jQuery.each( text || this, function(){
			jQuery.each( this.childNodes, function(){
				if ( this.nodeType != 8 )
					ret += this.nodeType != 1 ?
						this.nodeValue :
						jQuery.fn.text( [ this ] );
			});
		});

		return ret;
	},

	wrapAll: function( html ) {
		if ( this[0] ) {
			// The elements to wrap the target around
			var wrap = jQuery( html, this[0].ownerDocument ).clone();

			if ( this[0].parentNode )
				wrap.insertBefore( this[0] );

			wrap.map(function(){
				var elem = this;

				while ( elem.firstChild )
					elem = elem.firstChild;

				return elem;
			}).append(this);
		}

		return this;
	},

	wrapInner: function( html ) {
		return this.each(function(){
			jQuery( this ).contents().wrapAll( html );
		});
	},

	wrap: function( html ) {
		return this.each(function(){
			jQuery( this ).wrapAll( html );
		});
	},

	append: function() {
		return this.domManip(arguments, true, function(elem){
			if (this.nodeType == 1)
				this.appendChild( elem );
		});
	},

	prepend: function() {
		return this.domManip(arguments, true, function(elem){
			if (this.nodeType == 1)
				this.insertBefore( elem, this.firstChild );
		});
	},

	before: function() {
		return this.domManip(arguments, false, function(elem){
			this.parentNode.insertBefore( elem, this );
		});
	},

	after: function() {
		return this.domManip(arguments, false, function(elem){
			this.parentNode.insertBefore( elem, this.nextSibling );
		});
	},

	end: function() {
		return this.prevObject || jQuery( [] );
	},

	// For internal use only.
	// Behaves like an Array's method, not like a jQuery method.
	push: [].push,
	sort: [].sort,
	splice: [].splice,

	find: function( selector ) {
		if ( this.length === 1 ) {
			var ret = this.pushStack( [], "find", selector );
			ret.length = 0;
			jQuery.find( selector, this[0], ret );
			return ret;
		} else {
			return this.pushStack( jQuery.unique(jQuery.map(this, function(elem){
				return jQuery.find( selector, elem );
			})), "find", selector );
		}
	},

	clone: function( events ) {
		// Do the clone
		var ret = this.map(function(){
			if ( !jQuery.support.noCloneEvent && !jQuery.isXMLDoc(this) ) {
				// IE copies events bound via attachEvent when
				// using cloneNode. Calling detachEvent on the
				// clone will also remove the events from the orignal
				// In order to get around this, we use innerHTML.
				// Unfortunately, this means some modifications to
				// attributes in IE that are actually only stored
				// as properties will not be copied (such as the
				// the name attribute on an input).
				var html = this.outerHTML;
				if ( !html ) {
					var div = this.ownerDocument.createElement("div");
					div.appendChild( this.cloneNode(true) );
					html = div.innerHTML;
				}

				return jQuery.clean([html.replace(/ jQuery\d+="(?:\d+|null)"/g, "").replace(/^\s*/, "")])[0];
			} else
				return this.cloneNode(true);
		});

		// Copy the events from the original to the clone
		if ( events === true ) {
			var orig = this.find("*").andSelf(), i = 0;

			ret.find("*").andSelf().each(function(){
				if ( this.nodeName !== orig[i].nodeName )
					return;

				var events = jQuery.data( orig[i], "events" );

				for ( var type in events ) {
					for ( var handler in events[ type ] ) {
						jQuery.event.add( this, type, events[ type ][ handler ], events[ type ][ handler ].data );
					}
				}

				i++;
			});
		}

		// Return the cloned set
		return ret;
	},

	filter: function( selector ) {
		return this.pushStack(
			jQuery.isFunction( selector ) &&
			jQuery.grep(this, function(elem, i){
				return selector.call( elem, i );
			}) ||

			jQuery.multiFilter( selector, jQuery.grep(this, function(elem){
				return elem.nodeType === 1;
			}) ), "filter", selector );
	},

	closest: function( selector ) {
		var pos = jQuery.expr.match.POS.test( selector ) ? jQuery(selector) : null,
			closer = 0;

		return this.map(function(){
			var cur = this;
			while ( cur && cur.ownerDocument ) {
				if ( pos ? pos.index(cur) > -1 : jQuery(cur).is(selector) ) {
					jQuery.data(cur, "closest", closer);
					return cur;
				}
				cur = cur.parentNode;
				closer++;
			}
		});
	},

	not: function( selector ) {
		if ( typeof selector === "string" )
			// test special case where just one selector is passed in
			if ( isSimple.test( selector ) )
				return this.pushStack( jQuery.multiFilter( selector, this, true ), "not", selector );
			else
				selector = jQuery.multiFilter( selector, this );

		var isArrayLike = selector.length && selector[selector.length - 1] !== undefined && !selector.nodeType;
		return this.filter(function() {
			return isArrayLike ? jQuery.inArray( this, selector ) < 0 : this != selector;
		});
	},

	add: function( selector ) {
		return this.pushStack( jQuery.unique( jQuery.merge(
			this.get(),
			typeof selector === "string" ?
				jQuery( selector ) :
				jQuery.makeArray( selector )
		)));
	},

	is: function( selector ) {
		return !!selector && jQuery.multiFilter( selector, this ).length > 0;
	},

	hasClass: function( selector ) {
		return !!selector && this.is( "." + selector );
	},

	val: function( value ) {
		if ( value === undefined ) {			
			var elem = this[0];

			if ( elem ) {
				if( jQuery.nodeName( elem, 'option' ) )
					return (elem.attributes.value || {}).specified ? elem.value : elem.text;
				
				// We need to handle select boxes special
				if ( jQuery.nodeName( elem, "select" ) ) {
					var index = elem.selectedIndex,
						values = [],
						options = elem.options,
						one = elem.type == "select-one";

					// Nothing was selected
					if ( index < 0 )
						return null;

					// Loop through all the selected options
					for ( var i = one ? index : 0, max = one ? index + 1 : options.length; i < max; i++ ) {
						var option = options[ i ];

						if ( option.selected ) {
							// Get the specifc value for the option
							value = jQuery(option).val();

							// We don't need an array for one selects
							if ( one )
								return value;

							// Multi-Selects return an array
							values.push( value );
						}
					}

					return values;				
				}

				// Everything else, we just grab the value
				return (elem.value || "").replace(/\r/g, "");

			}

			return undefined;
		}

		if ( typeof value === "number" )
			value += '';

		return this.each(function(){
			if ( this.nodeType != 1 )
				return;

			if ( jQuery.isArray(value) && /radio|checkbox/.test( this.type ) )
				this.checked = (jQuery.inArray(this.value, value) >= 0 ||
					jQuery.inArray(this.name, value) >= 0);

			else if ( jQuery.nodeName( this, "select" ) ) {
				var values = jQuery.makeArray(value);

				jQuery( "option", this ).each(function(){
					this.selected = (jQuery.inArray( this.value, values ) >= 0 ||
						jQuery.inArray( this.text, values ) >= 0);
				});

				if ( !values.length )
					this.selectedIndex = -1;

			} else
				this.value = value;
		});
	},

	html: function( value ) {
		return value === undefined ?
			(this[0] ?
				this[0].innerHTML.replace(/ jQuery\d+="(?:\d+|null)"/g, "") :
				null) :
			this.empty().append( value );
	},

	replaceWith: function( value ) {
		return this.after( value ).remove();
	},

	eq: function( i ) {
		return this.slice( i, +i + 1 );
	},

	slice: function() {
		return this.pushStack( Array.prototype.slice.apply( this, arguments ),
			"slice", Array.prototype.slice.call(arguments).join(",") );
	},

	map: function( callback ) {
		return this.pushStack( jQuery.map(this, function(elem, i){
			return callback.call( elem, i, elem );
		}));
	},

	andSelf: function() {
		return this.add( this.prevObject );
	},

	domManip: function( args, table, callback ) {
		if ( this[0] ) {
			var fragment = (this[0].ownerDocument || this[0]).createDocumentFragment(),
				scripts = jQuery.clean( args, (this[0].ownerDocument || this[0]), fragment ),
				first = fragment.firstChild;

			if ( first )
				for ( var i = 0, l = this.length; i < l; i++ )
					callback.call( root(this[i], first), this.length > 1 || i > 0 ?
							fragment.cloneNode(true) : fragment );
		
			if ( scripts )
				jQuery.each( scripts, evalScript );
		}

		return this;
		
		function root( elem, cur ) {
			return table && jQuery.nodeName(elem, "table") && jQuery.nodeName(cur, "tr") ?
				(elem.getElementsByTagName("tbody")[0] ||
				elem.appendChild(elem.ownerDocument.createElement("tbody"))) :
				elem;
		}
	}
};

// Give the init function the jQuery prototype for later instantiation
jQuery.fn.init.prototype = jQuery.fn;

function evalScript( i, elem ) {
	if ( elem.src )
		jQuery.ajax({
			url: elem.src,
			async: false,
			dataType: "script"
		});

	else
		jQuery.globalEval( elem.text || elem.textContent || elem.innerHTML || "" );

	if ( elem.parentNode )
		elem.parentNode.removeChild( elem );
}

function now(){
	return +new Date;
}

jQuery.extend = jQuery.fn.extend = function() {
	// copy reference to target object
	var target = arguments[0] || {}, i = 1, length = arguments.length, deep = false, options;

	// Handle a deep copy situation
	if ( typeof target === "boolean" ) {
		deep = target;
		target = arguments[1] || {};
		// skip the boolean and the target
		i = 2;
	}

	// Handle case when target is a string or something (possible in deep copy)
	if ( typeof target !== "object" && !jQuery.isFunction(target) )
		target = {};

	// extend jQuery itself if only one argument is passed
	if ( length == i ) {
		target = this;
		--i;
	}

	for ( ; i < length; i++ )
		// Only deal with non-null/undefined values
		if ( (options = arguments[ i ]) != null )
			// Extend the base object
			for ( var name in options ) {
				var src = target[ name ], copy = options[ name ];

				// Prevent never-ending loop
				if ( target === copy )
					continue;

				// Recurse if we're merging object values
				if ( deep && copy && typeof copy === "object" && !copy.nodeType )
					target[ name ] = jQuery.extend( deep, 
						// Never move original objects, clone them
						src || ( copy.length != null ? [ ] : { } )
					, copy );

				// Don't bring in undefined values
				else if ( copy !== undefined )
					target[ name ] = copy;

			}

	// Return the modified object
	return target;
};

// exclude the following css properties to add px
var	exclude = /z-?index|font-?weight|opacity|zoom|line-?height/i,
	// cache defaultView
	defaultView = document.defaultView || {},
	toString = Object.prototype.toString;

jQuery.extend({
	noConflict: function( deep ) {
		window.$ = _$;

		if ( deep )
			window.jQuery = _jQuery;

		return jQuery;
	},

	// See test/unit/core.js for details concerning isFunction.
	// Since version 1.3, DOM methods and functions like alert
	// aren't supported. They return false on IE (#2968).
	isFunction: function( obj ) {
		return toString.call(obj) === "[object Function]";
	},

	isArray: function( obj ) {
		return toString.call(obj) === "[object Array]";
	},

	// check if an element is in a (or is an) XML document
	isXMLDoc: function( elem ) {
		return elem.nodeType === 9 && elem.documentElement.nodeName !== "HTML" ||
			!!elem.ownerDocument && jQuery.isXMLDoc( elem.ownerDocument );
	},

	// Evalulates a script in a global context
	globalEval: function( data ) {
		if ( data && /\S/.test(data) ) {
			// Inspired by code by Andrea Giammarchi
			// http://webreflection.blogspot.com/2007/08/global-scope-evaluation-and-dom.html
			var head = document.getElementsByTagName("head")[0] || document.documentElement,
				script = document.createElement("script");

			script.type = "text/javascript";
			if ( jQuery.support.scriptEval )
				script.appendChild( document.createTextNode( data ) );
			else
				script.text = data;

			// Use insertBefore instead of appendChild  to circumvent an IE6 bug.
			// This arises when a base node is used (#2709).
			head.insertBefore( script, head.firstChild );
			head.removeChild( script );
		}
	},

	nodeName: function( elem, name ) {
		return elem.nodeName && elem.nodeName.toUpperCase() == name.toUpperCase();
	},

	// args is for internal usage only
	each: function( object, callback, args ) {
		var name, i = 0, length = object.length;

		if ( args ) {
			if ( length === undefined ) {
				for ( name in object )
					if ( callback.apply( object[ name ], args ) === false )
						break;
			} else
				for ( ; i < length; )
					if ( callback.apply( object[ i++ ], args ) === false )
						break;

		// A special, fast, case for the most common use of each
		} else {
			if ( length === undefined ) {
				for ( name in object )
					if ( callback.call( object[ name ], name, object[ name ] ) === false )
						break;
			} else
				for ( var value = object[0];
					i < length && callback.call( value, i, value ) !== false; value = object[++i] ){}
		}

		return object;
	},

	prop: function( elem, value, type, i, name ) {
		// Handle executable functions
		if ( jQuery.isFunction( value ) )
			value = value.call( elem, i );

		// Handle passing in a number to a CSS property
		return typeof value === "number" && type == "curCSS" && !exclude.test( name ) ?
			value + "px" :
			value;
	},

	className: {
		// internal only, use addClass("class")
		add: function( elem, classNames ) {
			jQuery.each((classNames || "").split(/\s+/), function(i, className){
				if ( elem.nodeType == 1 && !jQuery.className.has( elem.className, className ) )
					elem.className += (elem.className ? " " : "") + className;
			});
		},

		// internal only, use removeClass("class")
		remove: function( elem, classNames ) {
			if (elem.nodeType == 1)
				elem.className = classNames !== undefined ?
					jQuery.grep(elem.className.split(/\s+/), function(className){
						return !jQuery.className.has( classNames, className );
					}).join(" ") :
					"";
		},

		// internal only, use hasClass("class")
		has: function( elem, className ) {
			return elem && jQuery.inArray( className, (elem.className || elem).toString().split(/\s+/) ) > -1;
		}
	},

	// A method for quickly swapping in/out CSS properties to get correct calculations
	swap: function( elem, options, callback ) {
		var old = {};
		// Remember the old values, and insert the new ones
		for ( var name in options ) {
			old[ name ] = elem.style[ name ];
			elem.style[ name ] = options[ name ];
		}

		callback.call( elem );

		// Revert the old values
		for ( var name in options )
			elem.style[ name ] = old[ name ];
	},

	css: function( elem, name, force, extra ) {
		if ( name == "width" || name == "height" ) {
			var val, props = { position: "absolute", visibility: "hidden", display:"block" }, which = name == "width" ? [ "Left", "Right" ] : [ "Top", "Bottom" ];

			function getWH() {
				val = name == "width" ? elem.offsetWidth : elem.offsetHeight;

				if ( extra === "border" )
					return;

				jQuery.each( which, function() {
					if ( !extra )
						val -= parseFloat(jQuery.curCSS( elem, "padding" + this, true)) || 0;
					if ( extra === "margin" )
						val += parseFloat(jQuery.curCSS( elem, "margin" + this, true)) || 0;
					else
						val -= parseFloat(jQuery.curCSS( elem, "border" + this + "Width", true)) || 0;
				});
			}

			if ( elem.offsetWidth !== 0 )
				getWH();
			else
				jQuery.swap( elem, props, getWH );

			return Math.max(0, Math.round(val));
		}

		return jQuery.curCSS( elem, name, force );
	},

	curCSS: function( elem, name, force ) {
		var ret, style = elem.style;

		// We need to handle opacity special in IE
		if ( name == "opacity" && !jQuery.support.opacity ) {
			ret = jQuery.attr( style, "opacity" );

			return ret == "" ?
				"1" :
				ret;
		}

		// Make sure we're using the right name for getting the float value
		if ( name.match( /float/i ) )
			name = styleFloat;

		if ( !force && style && style[ name ] )
			ret = style[ name ];

		else if ( defaultView.getComputedStyle ) {

			// Only "float" is needed here
			if ( name.match( /float/i ) )
				name = "float";

			name = name.replace( /([A-Z])/g, "-$1" ).toLowerCase();

			var computedStyle = defaultView.getComputedStyle( elem, null );

			if ( computedStyle )
				ret = computedStyle.getPropertyValue( name );

			// We should always get a number back from opacity
			if ( name == "opacity" && ret == "" )
				ret = "1";

		} else if ( elem.currentStyle ) {
			var camelCase = name.replace(/\-(\w)/g, function(all, letter){
				return letter.toUpperCase();
			});

			ret = elem.currentStyle[ name ] || elem.currentStyle[ camelCase ];

			// From the awesome hack by Dean Edwards
			// http://erik.eae.net/archives/2007/07/27/18.54.15/#comment-102291

			// If we're not dealing with a regular pixel number
			// but a number that has a weird ending, we need to convert it to pixels
			if ( !/^\d+(px)?$/i.test( ret ) && /^\d/.test( ret ) ) {
				// Remember the original values
				var left = style.left, rsLeft = elem.runtimeStyle.left;

				// Put in the new values to get a computed value out
				elem.runtimeStyle.left = elem.currentStyle.left;
				style.left = ret || 0;
				ret = style.pixelLeft + "px";

				// Revert the changed values
				style.left = left;
				elem.runtimeStyle.left = rsLeft;
			}
		}

		return ret;
	},

	clean: function( elems, context, fragment ) {
		context = context || document;

		// !context.createElement fails in IE with an error but returns typeof 'object'
		if ( typeof context.createElement === "undefined" )
			context = context.ownerDocument || context[0] && context[0].ownerDocument || document;

		// If a single string is passed in and it's a single tag
		// just do a createElement and skip the rest
		if ( !fragment && elems.length === 1 && typeof elems[0] === "string" ) {
			var match = /^<(\w+)\s*\/?>$/.exec(elems[0]);
			if ( match )
				return [ context.createElement( match[1] ) ];
		}

		var ret = [], scripts = [], div = context.createElement("div");

		jQuery.each(elems, function(i, elem){
			if ( typeof elem === "number" )
				elem += '';

			if ( !elem )
				return;

			// Convert html string into DOM nodes
			if ( typeof elem === "string" ) {
				// Fix "XHTML"-style tags in all browsers
				elem = elem.replace(/(<(\w+)[^>]*?)\/>/g, function(all, front, tag){
					return tag.match(/^(abbr|br|col|img|input|link|meta|param|hr|area|embed)$/i) ?
						all :
						front + "></" + tag + ">";
				});

				// Trim whitespace, otherwise indexOf won't work as expected
				var tags = elem.replace(/^\s+/, "").substring(0, 10).toLowerCase();

				var wrap =
					// option or optgroup
					!tags.indexOf("<opt") &&
					[ 1, "<select multiple='multiple'>", "</select>" ] ||

					!tags.indexOf("<leg") &&
					[ 1, "<fieldset>", "</fieldset>" ] ||

					tags.match(/^<(thead|tbody|tfoot|colg|cap)/) &&
					[ 1, "<table>", "</table>" ] ||

					!tags.indexOf("<tr") &&
					[ 2, "<table><tbody>", "</tbody></table>" ] ||

				 	// <thead> matched above
					(!tags.indexOf("<td") || !tags.indexOf("<th")) &&
					[ 3, "<table><tbody><tr>", "</tr></tbody></table>" ] ||

					!tags.indexOf("<col") &&
					[ 2, "<table><tbody></tbody><colgroup>", "</colgroup></table>" ] ||

					// IE can't serialize <link> and <script> tags normally
					!jQuery.support.htmlSerialize &&
					[ 1, "div<div>", "</div>" ] ||

					[ 0, "", "" ];

				// Go to html and back, then peel off extra wrappers
				div.innerHTML = wrap[1] + elem + wrap[2];

				// Move to the right depth
				while ( wrap[0]-- )
					div = div.lastChild;

				// Remove IE's autoinserted <tbody> from table fragments
				if ( !jQuery.support.tbody ) {

					// String was a <table>, *may* have spurious <tbody>
					var hasBody = /<tbody/i.test(elem),
						tbody = !tags.indexOf("<table") && !hasBody ?
							div.firstChild && div.firstChild.childNodes :

						// String was a bare <thead> or <tfoot>
						wrap[1] == "<table>" && !hasBody ?
							div.childNodes :
							[];

					for ( var j = tbody.length - 1; j >= 0 ; --j )
						if ( jQuery.nodeName( tbody[ j ], "tbody" ) && !tbody[ j ].childNodes.length )
							tbody[ j ].parentNode.removeChild( tbody[ j ] );

					}

				// IE completely kills leading whitespace when innerHTML is used
				if ( !jQuery.support.leadingWhitespace && /^\s/.test( elem ) )
					div.insertBefore( context.createTextNode( elem.match(/^\s*/)[0] ), div.firstChild );
				
				elem = jQuery.makeArray( div.childNodes );
			}

			if ( elem.nodeType )
				ret.push( elem );
			else
				ret = jQuery.merge( ret, elem );

		});

		if ( fragment ) {
			for ( var i = 0; ret[i]; i++ ) {
				if ( jQuery.nodeName( ret[i], "script" ) && (!ret[i].type || ret[i].type.toLowerCase() === "text/javascript") ) {
					scripts.push( ret[i].parentNode ? ret[i].parentNode.removeChild( ret[i] ) : ret[i] );
				} else {
					if ( ret[i].nodeType === 1 )
						ret.splice.apply( ret, [i + 1, 0].concat(jQuery.makeArray(ret[i].getElementsByTagName("script"))) );
					fragment.appendChild( ret[i] );
				}
			}
			
			return scripts;
		}

		return ret;
	},

	attr: function( elem, name, value ) {
		// don't set attributes on text and comment nodes
		if (!elem || elem.nodeType == 3 || elem.nodeType == 8)
			return undefined;

		var notxml = !jQuery.isXMLDoc( elem ),
			// Whether we are setting (or getting)
			set = value !== undefined;

		// Try to normalize/fix the name
		name = notxml && jQuery.props[ name ] || name;

		// Only do all the following if this is a node (faster for style)
		// IE elem.getAttribute passes even for style
		if ( elem.tagName ) {

			// These attributes require special treatment
			var special = /href|src|style/.test( name );

			// Safari mis-reports the default selected property of a hidden option
			// Accessing the parent's selectedIndex property fixes it
			if ( name == "selected" && elem.parentNode )
				elem.parentNode.selectedIndex;

			// If applicable, access the attribute via the DOM 0 way
			if ( name in elem && notxml && !special ) {
				if ( set ){
					// We can't allow the type property to be changed (since it causes problems in IE)
					if ( name == "type" && jQuery.nodeName( elem, "input" ) && elem.parentNode )
						throw "type property can't be changed";

					elem[ name ] = value;
				}

				// browsers index elements by id/name on forms, give priority to attributes.
				if( jQuery.nodeName( elem, "form" ) && elem.getAttributeNode(name) )
					return elem.getAttributeNode( name ).nodeValue;

				// elem.tabIndex doesn't always return the correct value when it hasn't been explicitly set
				// http://fluidproject.org/blog/2008/01/09/getting-setting-and-removing-tabindex-values-with-javascript/
				if ( name == "tabIndex" ) {
					var attributeNode = elem.getAttributeNode( "tabIndex" );
					return attributeNode && attributeNode.specified
						? attributeNode.value
						: elem.nodeName.match(/(button|input|object|select|textarea)/i)
							? 0
							: elem.nodeName.match(/^(a|area)$/i) && elem.href
								? 0
								: undefined;
				}

				return elem[ name ];
			}

			if ( !jQuery.support.style && notxml &&  name == "style" )
				return jQuery.attr( elem.style, "cssText", value );

			if ( set )
				// convert the value to a string (all browsers do this but IE) see #1070
				elem.setAttribute( name, "" + value );

			var attr = !jQuery.support.hrefNormalized && notxml && special
					// Some attributes require a special call on IE
					? elem.getAttribute( name, 2 )
					: elem.getAttribute( name );

			// Non-existent attributes return null, we normalize to undefined
			return attr === null ? undefined : attr;
		}

		// elem is actually elem.style ... set the style

		// IE uses filters for opacity
		if ( !jQuery.support.opacity && name == "opacity" ) {
			if ( set ) {
				// IE has trouble with opacity if it does not have layout
				// Force it by setting the zoom level
				elem.zoom = 1;

				// Set the alpha filter to set the opacity
				elem.filter = (elem.filter || "").replace( /alpha\([^)]*\)/, "" ) +
					(parseInt( value ) + '' == "NaN" ? "" : "alpha(opacity=" + value * 100 + ")");
			}

			return elem.filter && elem.filter.indexOf("opacity=") >= 0 ?
				(parseFloat( elem.filter.match(/opacity=([^)]*)/)[1] ) / 100) + '':
				"";
		}

		name = name.replace(/-([a-z])/ig, function(all, letter){
			return letter.toUpperCase();
		});

		if ( set )
			elem[ name ] = value;

		return elem[ name ];
	},

	trim: function( text ) {
		return (text || "").replace( /^\s+|\s+$/g, "" );
	},

	makeArray: function( array ) {
		var ret = [];

		if( array != null ){
			var i = array.length;
			// The window, strings (and functions) also have 'length'
			if( i == null || typeof array === "string" || jQuery.isFunction(array) || array.setInterval )
				ret[0] = array;
			else
				while( i )
					ret[--i] = array[i];
		}

		return ret;
	},

	inArray: function( elem, array ) {
		for ( var i = 0, length = array.length; i < length; i++ )
		// Use === because on IE, window == document
			if ( array[ i ] === elem )
				return i;

		return -1;
	},

	merge: function( first, second ) {
		// We have to loop this way because IE & Opera overwrite the length
		// expando of getElementsByTagName
		var i = 0, elem, pos = first.length;
		// Also, we need to make sure that the correct elements are being returned
		// (IE returns comment nodes in a '*' query)
		if ( !jQuery.support.getAll ) {
			while ( (elem = second[ i++ ]) != null )
				if ( elem.nodeType != 8 )
					first[ pos++ ] = elem;

		} else
			while ( (elem = second[ i++ ]) != null )
				first[ pos++ ] = elem;

		return first;
	},

	unique: function( array ) {
		var ret = [], done = {};

		try {

			for ( var i = 0, length = array.length; i < length; i++ ) {
				var id = jQuery.data( array[ i ] );

				if ( !done[ id ] ) {
					done[ id ] = true;
					ret.push( array[ i ] );
				}
			}

		} catch( e ) {
			ret = array;
		}

		return ret;
	},

	grep: function( elems, callback, inv ) {
		var ret = [];

		// Go through the array, only saving the items
		// that pass the validator function
		for ( var i = 0, length = elems.length; i < length; i++ )
			if ( !inv != !callback( elems[ i ], i ) )
				ret.push( elems[ i ] );

		return ret;
	},

	map: function( elems, callback ) {
		var ret = [];

		// Go through the array, translating each of the items to their
		// new value (or values).
		for ( var i = 0, length = elems.length; i < length; i++ ) {
			var value = callback( elems[ i ], i );

			if ( value != null )
				ret[ ret.length ] = value;
		}

		return ret.concat.apply( [], ret );
	}
});

// Use of jQuery.browser is deprecated.
// It's included for backwards compatibility and plugins,
// although they should work to migrate away.

var userAgent = navigator.userAgent.toLowerCase();

// Figure out what browser is being used
jQuery.browser = {
	version: (userAgent.match( /.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/ ) || [0,'0'])[1],
	safari: /webkit/.test( userAgent ),
	opera: /opera/.test( userAgent ),
	msie: /msie/.test( userAgent ) && !/opera/.test( userAgent ),
	mozilla: /mozilla/.test( userAgent ) && !/(compatible|webkit)/.test( userAgent )
};

jQuery.each({
	parent: function(elem){return elem.parentNode;},
	parents: function(elem){return jQuery.dir(elem,"parentNode");},
	next: function(elem){return jQuery.nth(elem,2,"nextSibling");},
	prev: function(elem){return jQuery.nth(elem,2,"previousSibling");},
	nextAll: function(elem){return jQuery.dir(elem,"nextSibling");},
	prevAll: function(elem){return jQuery.dir(elem,"previousSibling");},
	siblings: function(elem){return jQuery.sibling(elem.parentNode.firstChild,elem);},
	children: function(elem){return jQuery.sibling(elem.firstChild);},
	contents: function(elem){return jQuery.nodeName(elem,"iframe")?elem.contentDocument||elem.contentWindow.document:jQuery.makeArray(elem.childNodes);}
}, function(name, fn){
	jQuery.fn[ name ] = function( selector ) {
		var ret = jQuery.map( this, fn );

		if ( selector && typeof selector == "string" )
			ret = jQuery.multiFilter( selector, ret );

		return this.pushStack( jQuery.unique( ret ), name, selector );
	};
});

jQuery.each({
	appendTo: "append",
	prependTo: "prepend",
	insertBefore: "before",
	insertAfter: "after",
	replaceAll: "replaceWith"
}, function(name, original){
	jQuery.fn[ name ] = function( selector ) {
		var ret = [], insert = jQuery( selector );

		for ( var i = 0, l = insert.length; i < l; i++ ) {
			var elems = (i > 0 ? this.clone(true) : this).get();
			jQuery.fn[ original ].apply( jQuery(insert[i]), elems );
			ret = ret.concat( elems );
		}

		return this.pushStack( ret, name, selector );
	};
});

jQuery.each({
	removeAttr: function( name ) {
		jQuery.attr( this, name, "" );
		if (this.nodeType == 1)
			this.removeAttribute( name );
	},

	addClass: function( classNames ) {
		jQuery.className.add( this, classNames );
	},

	removeClass: function( classNames ) {
		jQuery.className.remove( this, classNames );
	},

	toggleClass: function( classNames, state ) {
		if( typeof state !== "boolean" )
			state = !jQuery.className.has( this, classNames );
		jQuery.className[ state ? "add" : "remove" ]( this, classNames );
	},

	remove: function( selector ) {
		if ( !selector || jQuery.filter( selector, [ this ] ).length ) {
			// Prevent memory leaks
			jQuery( "*", this ).add([this]).each(function(){
				jQuery.event.remove(this);
				jQuery.removeData(this);
			});
			if (this.parentNode)
				this.parentNode.removeChild( this );
		}
	},

	empty: function() {
		// Remove element nodes and prevent memory leaks
		jQuery(this).children().remove();

		// Remove any remaining nodes
		while ( this.firstChild )
			this.removeChild( this.firstChild );
	}
}, function(name, fn){
	jQuery.fn[ name ] = function(){
		return this.each( fn, arguments );
	};
});

// Helper function used by the dimensions and offset modules
function num(elem, prop) {
	return elem[0] && parseInt( jQuery.curCSS(elem[0], prop, true), 10 ) || 0;
}
var expando = "jQuery" + now(), uuid = 0, windowData = {};

jQuery.extend({
	cache: {},

	data: function( elem, name, data ) {
		elem = elem == window ?
			windowData :
			elem;

		var id = elem[ expando ];

		// Compute a unique ID for the element
		if ( !id )
			id = elem[ expando ] = ++uuid;

		// Only generate the data cache if we're
		// trying to access or manipulate it
		if ( name && !jQuery.cache[ id ] )
			jQuery.cache[ id ] = {};

		// Prevent overriding the named cache with undefined values
		if ( data !== undefined )
			jQuery.cache[ id ][ name ] = data;

		// Return the named cache data, or the ID for the element
		return name ?
			jQuery.cache[ id ][ name ] :
			id;
	},

	removeData: function( elem, name ) {
		elem = elem == window ?
			windowData :
			elem;

		var id = elem[ expando ];

		// If we want to remove a specific section of the element's data
		if ( name ) {
			if ( jQuery.cache[ id ] ) {
				// Remove the section of cache data
				delete jQuery.cache[ id ][ name ];

				// If we've removed all the data, remove the element's cache
				name = "";

				for ( name in jQuery.cache[ id ] )
					break;

				if ( !name )
					jQuery.removeData( elem );
			}

		// Otherwise, we want to remove all of the element's data
		} else {
			// Clean up the element expando
			try {
				delete elem[ expando ];
			} catch(e){
				// IE has trouble directly removing the expando
				// but it's ok with using removeAttribute
				if ( elem.removeAttribute )
					elem.removeAttribute( expando );
			}

			// Completely remove the data cache
			delete jQuery.cache[ id ];
		}
	},
	queue: function( elem, type, data ) {
		if ( elem ){
	
			type = (type || "fx") + "queue";
	
			var q = jQuery.data( elem, type );
	
			if ( !q || jQuery.isArray(data) )
				q = jQuery.data( elem, type, jQuery.makeArray(data) );
			else if( data )
				q.push( data );
	
		}
		return q;
	},

	dequeue: function( elem, type ){
		var queue = jQuery.queue( elem, type ),
			fn = queue.shift();
		
		if( !type || type === "fx" )
			fn = queue[0];
			
		if( fn !== undefined )
			fn.call(elem);
	}
});

jQuery.fn.extend({
	data: function( key, value ){
		var parts = key.split(".");
		parts[1] = parts[1] ? "." + parts[1] : "";

		if ( value === undefined ) {
			var data = this.triggerHandler("getData" + parts[1] + "!", [parts[0]]);

			if ( data === undefined && this.length )
				data = jQuery.data( this[0], key );

			return data === undefined && parts[1] ?
				this.data( parts[0] ) :
				data;
		} else
			return this.trigger("setData" + parts[1] + "!", [parts[0], value]).each(function(){
				jQuery.data( this, key, value );
			});
	},

	removeData: function( key ){
		return this.each(function(){
			jQuery.removeData( this, key );
		});
	},
	queue: function(type, data){
		if ( typeof type !== "string" ) {
			data = type;
			type = "fx";
		}

		if ( data === undefined )
			return jQuery.queue( this[0], type );

		return this.each(function(){
			var queue = jQuery.queue( this, type, data );
			
			 if( type == "fx" && queue.length == 1 )
				queue[0].call(this);
		});
	},
	dequeue: function(type){
		return this.each(function(){
			jQuery.dequeue( this, type );
		});
	}
});/*!
 * Sizzle CSS Selector Engine - v0.9.3
 *  Copyright 2009, The Dojo Foundation
 *  Released under the MIT, BSD, and GPL Licenses.
 *  More information: http://sizzlejs.com/
 */
(function(){

var chunker = /((?:\((?:\([^()]+\)|[^()]+)+\)|\[(?:\[[^[\]]*\]|['"][^'"]*['"]|[^[\]'"]+)+\]|\\.|[^ >+~,(\[\\]+)+|[>+~])(\s*,\s*)?/g,
	done = 0,
	toString = Object.prototype.toString;

var Sizzle = function(selector, context, results, seed) {
	results = results || [];
	context = context || document;

	if ( context.nodeType !== 1 && context.nodeType !== 9 )
		return [];
	
	if ( !selector || typeof selector !== "string" ) {
		return results;
	}

	var parts = [], m, set, checkSet, check, mode, extra, prune = true;
	
	// Reset the position of the chunker regexp (start from head)
	chunker.lastIndex = 0;
	
	while ( (m = chunker.exec(selector)) !== null ) {
		parts.push( m[1] );
		
		if ( m[2] ) {
			extra = RegExp.rightContext;
			break;
		}
	}

	if ( parts.length > 1 && origPOS.exec( selector ) ) {
		if ( parts.length === 2 && Expr.relative[ parts[0] ] ) {
			set = posProcess( parts[0] + parts[1], context );
		} else {
			set = Expr.relative[ parts[0] ] ?
				[ context ] :
				Sizzle( parts.shift(), context );

			while ( parts.length ) {
				selector = parts.shift();

				if ( Expr.relative[ selector ] )
					selector += parts.shift();

				set = posProcess( selector, set );
			}
		}
	} else {
		var ret = seed ?
			{ expr: parts.pop(), set: makeArray(seed) } :
			Sizzle.find( parts.pop(), parts.length === 1 && context.parentNode ? context.parentNode : context, isXML(context) );
		set = Sizzle.filter( ret.expr, ret.set );

		if ( parts.length > 0 ) {
			checkSet = makeArray(set);
		} else {
			prune = false;
		}

		while ( parts.length ) {
			var cur = parts.pop(), pop = cur;

			if ( !Expr.relative[ cur ] ) {
				cur = "";
			} else {
				pop = parts.pop();
			}

			if ( pop == null ) {
				pop = context;
			}

			Expr.relative[ cur ]( checkSet, pop, isXML(context) );
		}
	}

	if ( !checkSet ) {
		checkSet = set;
	}

	if ( !checkSet ) {
		throw "Syntax error, unrecognized expression: " + (cur || selector);
	}

	if ( toString.call(checkSet) === "[object Array]" ) {
		if ( !prune ) {
			results.push.apply( results, checkSet );
		} else if ( context.nodeType === 1 ) {
			for ( var i = 0; checkSet[i] != null; i++ ) {
				if ( checkSet[i] && (checkSet[i] === true || checkSet[i].nodeType === 1 && contains(context, checkSet[i])) ) {
					results.push( set[i] );
				}
			}
		} else {
			for ( var i = 0; checkSet[i] != null; i++ ) {
				if ( checkSet[i] && checkSet[i].nodeType === 1 ) {
					results.push( set[i] );
				}
			}
		}
	} else {
		makeArray( checkSet, results );
	}

	if ( extra ) {
		Sizzle( extra, context, results, seed );

		if ( sortOrder ) {
			hasDuplicate = false;
			results.sort(sortOrder);

			if ( hasDuplicate ) {
				for ( var i = 1; i < results.length; i++ ) {
					if ( results[i] === results[i-1] ) {
						results.splice(i--, 1);
					}
				}
			}
		}
	}

	return results;
};

Sizzle.matches = function(expr, set){
	return Sizzle(expr, null, null, set);
};

Sizzle.find = function(expr, context, isXML){
	var set, match;

	if ( !expr ) {
		return [];
	}

	for ( var i = 0, l = Expr.order.length; i < l; i++ ) {
		var type = Expr.order[i], match;
		
		if ( (match = Expr.match[ type ].exec( expr )) ) {
			var left = RegExp.leftContext;

			if ( left.substr( left.length - 1 ) !== "\\" ) {
				match[1] = (match[1] || "").replace(/\\/g, "");
				set = Expr.find[ type ]( match, context, isXML );
				if ( set != null ) {
					expr = expr.replace( Expr.match[ type ], "" );
					break;
				}
			}
		}
	}

	if ( !set ) {
		set = context.getElementsByTagName("*");
	}

	return {set: set, expr: expr};
};

Sizzle.filter = function(expr, set, inplace, not){
	var old = expr, result = [], curLoop = set, match, anyFound,
		isXMLFilter = set && set[0] && isXML(set[0]);

	while ( expr && set.length ) {
		for ( var type in Expr.filter ) {
			if ( (match = Expr.match[ type ].exec( expr )) != null ) {
				var filter = Expr.filter[ type ], found, item;
				anyFound = false;

				if ( curLoop == result ) {
					result = [];
				}

				if ( Expr.preFilter[ type ] ) {
					match = Expr.preFilter[ type ]( match, curLoop, inplace, result, not, isXMLFilter );

					if ( !match ) {
						anyFound = found = true;
					} else if ( match === true ) {
						continue;
					}
				}

				if ( match ) {
					for ( var i = 0; (item = curLoop[i]) != null; i++ ) {
						if ( item ) {
							found = filter( item, match, i, curLoop );
							var pass = not ^ !!found;

							if ( inplace && found != null ) {
								if ( pass ) {
									anyFound = true;
								} else {
									curLoop[i] = false;
								}
							} else if ( pass ) {
								result.push( item );
								anyFound = true;
							}
						}
					}
				}

				if ( found !== undefined ) {
					if ( !inplace ) {
						curLoop = result;
					}

					expr = expr.replace( Expr.match[ type ], "" );

					if ( !anyFound ) {
						return [];
					}

					break;
				}
			}
		}

		// Improper expression
		if ( expr == old ) {
			if ( anyFound == null ) {
				throw "Syntax error, unrecognized expression: " + expr;
			} else {
				break;
			}
		}

		old = expr;
	}

	return curLoop;
};

var Expr = Sizzle.selectors = {
	order: [ "ID", "NAME", "TAG" ],
	match: {
		ID: /#((?:[\w\u00c0-\uFFFF_-]|\\.)+)/,
		CLASS: /\.((?:[\w\u00c0-\uFFFF_-]|\\.)+)/,
		NAME: /\[name=['"]*((?:[\w\u00c0-\uFFFF_-]|\\.)+)['"]*\]/,
		ATTR: /\[\s*((?:[\w\u00c0-\uFFFF_-]|\\.)+)\s*(?:(\S?=)\s*(['"]*)(.*?)\3|)\s*\]/,
		TAG: /^((?:[\w\u00c0-\uFFFF\*_-]|\\.)+)/,
		CHILD: /:(only|nth|last|first)-child(?:\((even|odd|[\dn+-]*)\))?/,
		POS: /:(nth|eq|gt|lt|first|last|even|odd)(?:\((\d*)\))?(?=[^-]|$)/,
		PSEUDO: /:((?:[\w\u00c0-\uFFFF_-]|\\.)+)(?:\((['"]*)((?:\([^\)]+\)|[^\2\(\)]*)+)\2\))?/
	},
	attrMap: {
		"class": "className",
		"for": "htmlFor"
	},
	attrHandle: {
		href: function(elem){
			return elem.getAttribute("href");
		}
	},
	relative: {
		"+": function(checkSet, part, isXML){
			var isPartStr = typeof part === "string",
				isTag = isPartStr && !/\W/.test(part),
				isPartStrNotTag = isPartStr && !isTag;

			if ( isTag && !isXML ) {
				part = part.toUpperCase();
			}

			for ( var i = 0, l = checkSet.length, elem; i < l; i++ ) {
				if ( (elem = checkSet[i]) ) {
					while ( (elem = elem.previousSibling) && elem.nodeType !== 1 ) {}

					checkSet[i] = isPartStrNotTag || elem && elem.nodeName === part ?
						elem || false :
						elem === part;
				}
			}

			if ( isPartStrNotTag ) {
				Sizzle.filter( part, checkSet, true );
			}
		},
		">": function(checkSet, part, isXML){
			var isPartStr = typeof part === "string";

			if ( isPartStr && !/\W/.test(part) ) {
				part = isXML ? part : part.toUpperCase();

				for ( var i = 0, l = checkSet.length; i < l; i++ ) {
					var elem = checkSet[i];
					if ( elem ) {
						var parent = elem.parentNode;
						checkSet[i] = parent.nodeName === part ? parent : false;
					}
				}
			} else {
				for ( var i = 0, l = checkSet.length; i < l; i++ ) {
					var elem = checkSet[i];
					if ( elem ) {
						checkSet[i] = isPartStr ?
							elem.parentNode :
							elem.parentNode === part;
					}
				}

				if ( isPartStr ) {
					Sizzle.filter( part, checkSet, true );
				}
			}
		},
		"": function(checkSet, part, isXML){
			var doneName = done++, checkFn = dirCheck;

			if ( !part.match(/\W/) ) {
				var nodeCheck = part = isXML ? part : part.toUpperCase();
				checkFn = dirNodeCheck;
			}

			checkFn("parentNode", part, doneName, checkSet, nodeCheck, isXML);
		},
		"~": function(checkSet, part, isXML){
			var doneName = done++, checkFn = dirCheck;

			if ( typeof part === "string" && !part.match(/\W/) ) {
				var nodeCheck = part = isXML ? part : part.toUpperCase();
				checkFn = dirNodeCheck;
			}

			checkFn("previousSibling", part, doneName, checkSet, nodeCheck, isXML);
		}
	},
	find: {
		ID: function(match, context, isXML){
			if ( typeof context.getElementById !== "undefined" && !isXML ) {
				var m = context.getElementById(match[1]);
				return m ? [m] : [];
			}
		},
		NAME: function(match, context, isXML){
			if ( typeof context.getElementsByName !== "undefined" ) {
				var ret = [], results = context.getElementsByName(match[1]);

				for ( var i = 0, l = results.length; i < l; i++ ) {
					if ( results[i].getAttribute("name") === match[1] ) {
						ret.push( results[i] );
					}
				}

				return ret.length === 0 ? null : ret;
			}
		},
		TAG: function(match, context){
			return context.getElementsByTagName(match[1]);
		}
	},
	preFilter: {
		CLASS: function(match, curLoop, inplace, result, not, isXML){
			match = " " + match[1].replace(/\\/g, "") + " ";

			if ( isXML ) {
				return match;
			}

			for ( var i = 0, elem; (elem = curLoop[i]) != null; i++ ) {
				if ( elem ) {
					if ( not ^ (elem.className && (" " + elem.className + " ").indexOf(match) >= 0) ) {
						if ( !inplace )
							result.push( elem );
					} else if ( inplace ) {
						curLoop[i] = false;
					}
				}
			}

			return false;
		},
		ID: function(match){
			return match[1].replace(/\\/g, "");
		},
		TAG: function(match, curLoop){
			for ( var i = 0; curLoop[i] === false; i++ ){}
			return curLoop[i] && isXML(curLoop[i]) ? match[1] : match[1].toUpperCase();
		},
		CHILD: function(match){
			if ( match[1] == "nth" ) {
				// parse equations like 'even', 'odd', '5', '2n', '3n+2', '4n-1', '-n+6'
				var test = /(-?)(\d*)n((?:\+|-)?\d*)/.exec(
					match[2] == "even" && "2n" || match[2] == "odd" && "2n+1" ||
					!/\D/.test( match[2] ) && "0n+" + match[2] || match[2]);

				// calculate the numbers (first)n+(last) including if they are negative
				match[2] = (test[1] + (test[2] || 1)) - 0;
				match[3] = test[3] - 0;
			}

			// TODO: Move to normal caching system
			match[0] = done++;

			return match;
		},
		ATTR: function(match, curLoop, inplace, result, not, isXML){
			var name = match[1].replace(/\\/g, "");
			
			if ( !isXML && Expr.attrMap[name] ) {
				match[1] = Expr.attrMap[name];
			}

			if ( match[2] === "~=" ) {
				match[4] = " " + match[4] + " ";
			}

			return match;
		},
		PSEUDO: function(match, curLoop, inplace, result, not){
			if ( match[1] === "not" ) {
				// If we're dealing with a complex expression, or a simple one
				if ( match[3].match(chunker).length > 1 || /^\w/.test(match[3]) ) {
					match[3] = Sizzle(match[3], null, null, curLoop);
				} else {
					var ret = Sizzle.filter(match[3], curLoop, inplace, true ^ not);
					if ( !inplace ) {
						result.push.apply( result, ret );
					}
					return false;
				}
			} else if ( Expr.match.POS.test( match[0] ) || Expr.match.CHILD.test( match[0] ) ) {
				return true;
			}
			
			return match;
		},
		POS: function(match){
			match.unshift( true );
			return match;
		}
	},
	filters: {
		enabled: function(elem){
			return elem.disabled === false && elem.type !== "hidden";
		},
		disabled: function(elem){
			return elem.disabled === true;
		},
		checked: function(elem){
			return elem.checked === true;
		},
		selected: function(elem){
			// Accessing this property makes selected-by-default
			// options in Safari work properly
			elem.parentNode.selectedIndex;
			return elem.selected === true;
		},
		parent: function(elem){
			return !!elem.firstChild;
		},
		empty: function(elem){
			return !elem.firstChild;
		},
		has: function(elem, i, match){
			return !!Sizzle( match[3], elem ).length;
		},
		header: function(elem){
			return /h\d/i.test( elem.nodeName );
		},
		text: function(elem){
			return "text" === elem.type;
		},
		radio: function(elem){
			return "radio" === elem.type;
		},
		checkbox: function(elem){
			return "checkbox" === elem.type;
		},
		file: function(elem){
			return "file" === elem.type;
		},
		password: function(elem){
			return "password" === elem.type;
		},
		submit: function(elem){
			return "submit" === elem.type;
		},
		image: function(elem){
			return "image" === elem.type;
		},
		reset: function(elem){
			return "reset" === elem.type;
		},
		button: function(elem){
			return "button" === elem.type || elem.nodeName.toUpperCase() === "BUTTON";
		},
		input: function(elem){
			return /input|select|textarea|button/i.test(elem.nodeName);
		}
	},
	setFilters: {
		first: function(elem, i){
			return i === 0;
		},
		last: function(elem, i, match, array){
			return i === array.length - 1;
		},
		even: function(elem, i){
			return i % 2 === 0;
		},
		odd: function(elem, i){
			return i % 2 === 1;
		},
		lt: function(elem, i, match){
			return i < match[3] - 0;
		},
		gt: function(elem, i, match){
			return i > match[3] - 0;
		},
		nth: function(elem, i, match){
			return match[3] - 0 == i;
		},
		eq: function(elem, i, match){
			return match[3] - 0 == i;
		}
	},
	filter: {
		PSEUDO: function(elem, match, i, array){
			var name = match[1], filter = Expr.filters[ name ];

			if ( filter ) {
				return filter( elem, i, match, array );
			} else if ( name === "contains" ) {
				return (elem.textContent || elem.innerText || "").indexOf(match[3]) >= 0;
			} else if ( name === "not" ) {
				var not = match[3];

				for ( var i = 0, l = not.length; i < l; i++ ) {
					if ( not[i] === elem ) {
						return false;
					}
				}

				return true;
			}
		},
		CHILD: function(elem, match){
			var type = match[1], node = elem;
			switch (type) {
				case 'only':
				case 'first':
					while (node = node.previousSibling)  {
						if ( node.nodeType === 1 ) return false;
					}
					if ( type == 'first') return true;
					node = elem;
				case 'last':
					while (node = node.nextSibling)  {
						if ( node.nodeType === 1 ) return false;
					}
					return true;
				case 'nth':
					var first = match[2], last = match[3];

					if ( first == 1 && last == 0 ) {
						return true;
					}
					
					var doneName = match[0],
						parent = elem.parentNode;
	
					if ( parent && (parent.sizcache !== doneName || !elem.nodeIndex) ) {
						var count = 0;
						for ( node = parent.firstChild; node; node = node.nextSibling ) {
							if ( node.nodeType === 1 ) {
								node.nodeIndex = ++count;
							}
						} 
						parent.sizcache = doneName;
					}
					
					var diff = elem.nodeIndex - last;
					if ( first == 0 ) {
						return diff == 0;
					} else {
						return ( diff % first == 0 && diff / first >= 0 );
					}
			}
		},
		ID: function(elem, match){
			return elem.nodeType === 1 && elem.getAttribute("id") === match;
		},
		TAG: function(elem, match){
			return (match === "*" && elem.nodeType === 1) || elem.nodeName === match;
		},
		CLASS: function(elem, match){
			return (" " + (elem.className || elem.getAttribute("class")) + " ")
				.indexOf( match ) > -1;
		},
		ATTR: function(elem, match){
			var name = match[1],
				result = Expr.attrHandle[ name ] ?
					Expr.attrHandle[ name ]( elem ) :
					elem[ name ] != null ?
						elem[ name ] :
						elem.getAttribute( name ),
				value = result + "",
				type = match[2],
				check = match[4];

			return result == null ?
				type === "!=" :
				type === "=" ?
				value === check :
				type === "*=" ?
				value.indexOf(check) >= 0 :
				type === "~=" ?
				(" " + value + " ").indexOf(check) >= 0 :
				!check ?
				value && result !== false :
				type === "!=" ?
				value != check :
				type === "^=" ?
				value.indexOf(check) === 0 :
				type === "$=" ?
				value.substr(value.length - check.length) === check :
				type === "|=" ?
				value === check || value.substr(0, check.length + 1) === check + "-" :
				false;
		},
		POS: function(elem, match, i, array){
			var name = match[2], filter = Expr.setFilters[ name ];

			if ( filter ) {
				return filter( elem, i, match, array );
			}
		}
	}
};

var origPOS = Expr.match.POS;

for ( var type in Expr.match ) {
	Expr.match[ type ] = RegExp( Expr.match[ type ].source + /(?![^\[]*\])(?![^\(]*\))/.source );
}

var makeArray = function(array, results) {
	array = Array.prototype.slice.call( array );

	if ( results ) {
		results.push.apply( results, array );
		return results;
	}
	
	return array;
};

// Perform a simple check to determine if the browser is capable of
// converting a NodeList to an array using builtin methods.
try {
	Array.prototype.slice.call( document.documentElement.childNodes );

// Provide a fallback method if it does not work
} catch(e){
	makeArray = function(array, results) {
		var ret = results || [];

		if ( toString.call(array) === "[object Array]" ) {
			Array.prototype.push.apply( ret, array );
		} else {
			if ( typeof array.length === "number" ) {
				for ( var i = 0, l = array.length; i < l; i++ ) {
					ret.push( array[i] );
				}
			} else {
				for ( var i = 0; array[i]; i++ ) {
					ret.push( array[i] );
				}
			}
		}

		return ret;
	};
}

var sortOrder;

if ( document.documentElement.compareDocumentPosition ) {
	sortOrder = function( a, b ) {
		var ret = a.compareDocumentPosition(b) & 4 ? -1 : a === b ? 0 : 1;
		if ( ret === 0 ) {
			hasDuplicate = true;
		}
		return ret;
	};
} else if ( "sourceIndex" in document.documentElement ) {
	sortOrder = function( a, b ) {
		var ret = a.sourceIndex - b.sourceIndex;
		if ( ret === 0 ) {
			hasDuplicate = true;
		}
		return ret;
	};
} else if ( document.createRange ) {
	sortOrder = function( a, b ) {
		var aRange = a.ownerDocument.createRange(), bRange = b.ownerDocument.createRange();
		aRange.selectNode(a);
		aRange.collapse(true);
		bRange.selectNode(b);
		bRange.collapse(true);
		var ret = aRange.compareBoundaryPoints(Range.START_TO_END, bRange);
		if ( ret === 0 ) {
			hasDuplicate = true;
		}
		return ret;
	};
}

// Check to see if the browser returns elements by name when
// querying by getElementById (and provide a workaround)
(function(){
	// We're going to inject a fake input element with a specified name
	var form = document.createElement("form"),
		id = "script" + (new Date).getTime();
	form.innerHTML = "<input name='" + id + "'/>";

	// Inject it into the root element, check its status, and remove it quickly
	var root = document.documentElement;
	root.insertBefore( form, root.firstChild );

	// The workaround has to do additional checks after a getElementById
	// Which slows things down for other browsers (hence the branching)
	if ( !!document.getElementById( id ) ) {
		Expr.find.ID = function(match, context, isXML){
			if ( typeof context.getElementById !== "undefined" && !isXML ) {
				var m = context.getElementById(match[1]);
				return m ? m.id === match[1] || typeof m.getAttributeNode !== "undefined" && m.getAttributeNode("id").nodeValue === match[1] ? [m] : undefined : [];
			}
		};

		Expr.filter.ID = function(elem, match){
			var node = typeof elem.getAttributeNode !== "undefined" && elem.getAttributeNode("id");
			return elem.nodeType === 1 && node && node.nodeValue === match;
		};
	}

	root.removeChild( form );
})();

(function(){
	// Check to see if the browser returns only elements
	// when doing getElementsByTagName("*")

	// Create a fake element
	var div = document.createElement("div");
	div.appendChild( document.createComment("") );

	// Make sure no comments are found
	if ( div.getElementsByTagName("*").length > 0 ) {
		Expr.find.TAG = function(match, context){
			var results = context.getElementsByTagName(match[1]);

			// Filter out possible comments
			if ( match[1] === "*" ) {
				var tmp = [];

				for ( var i = 0; results[i]; i++ ) {
					if ( results[i].nodeType === 1 ) {
						tmp.push( results[i] );
					}
				}

				results = tmp;
			}

			return results;
		};
	}

	// Check to see if an attribute returns normalized href attributes
	div.innerHTML = "<a href='#'></a>";
	if ( div.firstChild && typeof div.firstChild.getAttribute !== "undefined" &&
			div.firstChild.getAttribute("href") !== "#" ) {
		Expr.attrHandle.href = function(elem){
			return elem.getAttribute("href", 2);
		};
	}
})();

if ( document.querySelectorAll ) (function(){
	var oldSizzle = Sizzle, div = document.createElement("div");
	div.innerHTML = "<p class='TEST'></p>";

	// Safari can't handle uppercase or unicode characters when
	// in quirks mode.
	if ( div.querySelectorAll && div.querySelectorAll(".TEST").length === 0 ) {
		return;
	}
	
	Sizzle = function(query, context, extra, seed){
		context = context || document;

		// Only use querySelectorAll on non-XML documents
		// (ID selectors don't work in non-HTML documents)
		if ( !seed && context.nodeType === 9 && !isXML(context) ) {
			try {
				return makeArray( context.querySelectorAll(query), extra );
			} catch(e){}
		}
		
		return oldSizzle(query, context, extra, seed);
	};

	Sizzle.find = oldSizzle.find;
	Sizzle.filter = oldSizzle.filter;
	Sizzle.selectors = oldSizzle.selectors;
	Sizzle.matches = oldSizzle.matches;
})();

if ( document.getElementsByClassName && document.documentElement.getElementsByClassName ) (function(){
	var div = document.createElement("div");
	div.innerHTML = "<div class='test e'></div><div class='test'></div>";

	// Opera can't find a second classname (in 9.6)
	if ( div.getElementsByClassName("e").length === 0 )
		return;

	// Safari caches class attributes, doesn't catch changes (in 3.2)
	div.lastChild.className = "e";

	if ( div.getElementsByClassName("e").length === 1 )
		return;

	Expr.order.splice(1, 0, "CLASS");
	Expr.find.CLASS = function(match, context, isXML) {
		if ( typeof context.getElementsByClassName !== "undefined" && !isXML ) {
			return context.getElementsByClassName(match[1]);
		}
	};
})();

function dirNodeCheck( dir, cur, doneName, checkSet, nodeCheck, isXML ) {
	var sibDir = dir == "previousSibling" && !isXML;
	for ( var i = 0, l = checkSet.length; i < l; i++ ) {
		var elem = checkSet[i];
		if ( elem ) {
			if ( sibDir && elem.nodeType === 1 ){
				elem.sizcache = doneName;
				elem.sizset = i;
			}
			elem = elem[dir];
			var match = false;

			while ( elem ) {
				if ( elem.sizcache === doneName ) {
					match = checkSet[elem.sizset];
					break;
				}

				if ( elem.nodeType === 1 && !isXML ){
					elem.sizcache = doneName;
					elem.sizset = i;
				}

				if ( elem.nodeName === cur ) {
					match = elem;
					break;
				}

				elem = elem[dir];
			}

			checkSet[i] = match;
		}
	}
}

function dirCheck( dir, cur, doneName, checkSet, nodeCheck, isXML ) {
	var sibDir = dir == "previousSibling" && !isXML;
	for ( var i = 0, l = checkSet.length; i < l; i++ ) {
		var elem = checkSet[i];
		if ( elem ) {
			if ( sibDir && elem.nodeType === 1 ) {
				elem.sizcache = doneName;
				elem.sizset = i;
			}
			elem = elem[dir];
			var match = false;

			while ( elem ) {
				if ( elem.sizcache === doneName ) {
					match = checkSet[elem.sizset];
					break;
				}

				if ( elem.nodeType === 1 ) {
					if ( !isXML ) {
						elem.sizcache = doneName;
						elem.sizset = i;
					}
					if ( typeof cur !== "string" ) {
						if ( elem === cur ) {
							match = true;
							break;
						}

					} else if ( Sizzle.filter( cur, [elem] ).length > 0 ) {
						match = elem;
						break;
					}
				}

				elem = elem[dir];
			}

			checkSet[i] = match;
		}
	}
}

var contains = document.compareDocumentPosition ?  function(a, b){
	return a.compareDocumentPosition(b) & 16;
} : function(a, b){
	return a !== b && (a.contains ? a.contains(b) : true);
};

var isXML = function(elem){
	return elem.nodeType === 9 && elem.documentElement.nodeName !== "HTML" ||
		!!elem.ownerDocument && isXML( elem.ownerDocument );
};

var posProcess = function(selector, context){
	var tmpSet = [], later = "", match,
		root = context.nodeType ? [context] : context;

	// Position selectors must be done after the filter
	// And so must :not(positional) so we move all PSEUDOs to the end
	while ( (match = Expr.match.PSEUDO.exec( selector )) ) {
		later += match[0];
		selector = selector.replace( Expr.match.PSEUDO, "" );
	}

	selector = Expr.relative[selector] ? selector + "*" : selector;

	for ( var i = 0, l = root.length; i < l; i++ ) {
		Sizzle( selector, root[i], tmpSet );
	}

	return Sizzle.filter( later, tmpSet );
};

// EXPOSE
jQuery.find = Sizzle;
jQuery.filter = Sizzle.filter;
jQuery.expr = Sizzle.selectors;
jQuery.expr[":"] = jQuery.expr.filters;

Sizzle.selectors.filters.hidden = function(elem){
	return elem.offsetWidth === 0 || elem.offsetHeight === 0;
};

Sizzle.selectors.filters.visible = function(elem){
	return elem.offsetWidth > 0 || elem.offsetHeight > 0;
};

Sizzle.selectors.filters.animated = function(elem){
	return jQuery.grep(jQuery.timers, function(fn){
		return elem === fn.elem;
	}).length;
};

jQuery.multiFilter = function( expr, elems, not ) {
	if ( not ) {
		expr = ":not(" + expr + ")";
	}

	return Sizzle.matches(expr, elems);
};

jQuery.dir = function( elem, dir ){
	var matched = [], cur = elem[dir];
	while ( cur && cur != document ) {
		if ( cur.nodeType == 1 )
			matched.push( cur );
		cur = cur[dir];
	}
	return matched;
};

jQuery.nth = function(cur, result, dir, elem){
	result = result || 1;
	var num = 0;

	for ( ; cur; cur = cur[dir] )
		if ( cur.nodeType == 1 && ++num == result )
			break;

	return cur;
};

jQuery.sibling = function(n, elem){
	var r = [];

	for ( ; n; n = n.nextSibling ) {
		if ( n.nodeType == 1 && n != elem )
			r.push( n );
	}

	return r;
};

return;

window.Sizzle = Sizzle;

})();
/*
 * A number of helper functions used for managing events.
 * Many of the ideas behind this code originated from
 * Dean Edwards' addEvent library.
 */
jQuery.event = {

	// Bind an event to an element
	// Original by Dean Edwards
	add: function(elem, types, handler, data) {
		if ( elem.nodeType == 3 || elem.nodeType == 8 )
			return;

		// For whatever reason, IE has trouble passing the window object
		// around, causing it to be cloned in the process
		if ( elem.setInterval && elem != window )
			elem = window;

		// Make sure that the function being executed has a unique ID
		if ( !handler.guid )
			handler.guid = this.guid++;

		// if data is passed, bind to handler
		if ( data !== undefined ) {
			// Create temporary function pointer to original handler
			var fn = handler;

			// Create unique handler function, wrapped around original handler
			handler = this.proxy( fn );

			// Store data in unique handler
			handler.data = data;
		}

		// Init the element's event structure
		var events = jQuery.data(elem, "events") || jQuery.data(elem, "events", {}),
			handle = jQuery.data(elem, "handle") || jQuery.data(elem, "handle", function(){
				// Handle the second event of a trigger and when
				// an event is called after a page has unloaded
				return typeof jQuery !== "undefined" && !jQuery.event.triggered ?
					jQuery.event.handle.apply(arguments.callee.elem, arguments) :
					undefined;
			});
		// Add elem as a property of the handle function
		// This is to prevent a memory leak with non-native
		// event in IE.
		handle.elem = elem;

		// Handle multiple events separated by a space
		// jQuery(...).bind("mouseover mouseout", fn);
		jQuery.each(types.split(/\s+/), function(index, type) {
			// Namespaced event handlers
			var namespaces = type.split(".");
			type = namespaces.shift();
			handler.type = namespaces.slice().sort().join(".");

			// Get the current list of functions bound to this event
			var handlers = events[type];
			
			if ( jQuery.event.specialAll[type] )
				jQuery.event.specialAll[type].setup.call(elem, data, namespaces);

			// Init the event handler queue
			if (!handlers) {
				handlers = events[type] = {};

				// Check for a special event handler
				// Only use addEventListener/attachEvent if the special
				// events handler returns false
				if ( !jQuery.event.special[type] || jQuery.event.special[type].setup.call(elem, data, namespaces) === false ) {
					// Bind the global event handler to the element
					if (elem.addEventListener)
						elem.addEventListener(type, handle, false);
					else if (elem.attachEvent)
						elem.attachEvent("on" + type, handle);
				}
			}

			// Add the function to the element's handler list
			handlers[handler.guid] = handler;

			// Keep track of which events have been used, for global triggering
			jQuery.event.global[type] = true;
		});

		// Nullify elem to prevent memory leaks in IE
		elem = null;
	},

	guid: 1,
	global: {},

	// Detach an event or set of events from an element
	remove: function(elem, types, handler) {
		// don't do events on text and comment nodes
		if ( elem.nodeType == 3 || elem.nodeType == 8 )
			return;

		var events = jQuery.data(elem, "events"), ret, index;

		if ( events ) {
			// Unbind all events for the element
			if ( types === undefined || (typeof types === "string" && types.charAt(0) == ".") )
				for ( var type in events )
					this.remove( elem, type + (types || "") );
			else {
				// types is actually an event object here
				if ( types.type ) {
					handler = types.handler;
					types = types.type;
				}

				// Handle multiple events seperated by a space
				// jQuery(...).unbind("mouseover mouseout", fn);
				jQuery.each(types.split(/\s+/), function(index, type){
					// Namespaced event handlers
					var namespaces = type.split(".");
					type = namespaces.shift();
					var namespace = RegExp("(^|\\.)" + namespaces.slice().sort().join(".*\\.") + "(\\.|$)");

					if ( events[type] ) {
						// remove the given handler for the given type
						if ( handler )
							delete events[type][handler.guid];

						// remove all handlers for the given type
						else
							for ( var handle in events[type] )
								// Handle the removal of namespaced events
								if ( namespace.test(events[type][handle].type) )
									delete events[type][handle];
									
						if ( jQuery.event.specialAll[type] )
							jQuery.event.specialAll[type].teardown.call(elem, namespaces);

						// remove generic event handler if no more handlers exist
						for ( ret in events[type] ) break;
						if ( !ret ) {
							if ( !jQuery.event.special[type] || jQuery.event.special[type].teardown.call(elem, namespaces) === false ) {
								if (elem.removeEventListener)
									elem.removeEventListener(type, jQuery.data(elem, "handle"), false);
								else if (elem.detachEvent)
									elem.detachEvent("on" + type, jQuery.data(elem, "handle"));
							}
							ret = null;
							delete events[type];
						}
					}
				});
			}

			// Remove the expando if it's no longer used
			for ( ret in events ) break;
			if ( !ret ) {
				var handle = jQuery.data( elem, "handle" );
				if ( handle ) handle.elem = null;
				jQuery.removeData( elem, "events" );
				jQuery.removeData( elem, "handle" );
			}
		}
	},

	// bubbling is internal
	trigger: function( event, data, elem, bubbling ) {
		// Event object or event type
		var type = event.type || event;

		if( !bubbling ){
			event = typeof event === "object" ?
				// jQuery.Event object
				event[expando] ? event :
				// Object literal
				jQuery.extend( jQuery.Event(type), event ) :
				// Just the event type (string)
				jQuery.Event(type);

			if ( type.indexOf("!") >= 0 ) {
				event.type = type = type.slice(0, -1);
				event.exclusive = true;
			}

			// Handle a global trigger
			if ( !elem ) {
				// Don't bubble custom events when global (to avoid too much overhead)
				event.stopPropagation();
				// Only trigger if we've ever bound an event for it
				if ( this.global[type] )
					jQuery.each( jQuery.cache, function(){
						if ( this.events && this.events[type] )
							jQuery.event.trigger( event, data, this.handle.elem );
					});
			}

			// Handle triggering a single element

			// don't do events on text and comment nodes
			if ( !elem || elem.nodeType == 3 || elem.nodeType == 8 )
				return undefined;
			
			// Clean up in case it is reused
			event.result = undefined;
			event.target = elem;
			
			// Clone the incoming data, if any
			data = jQuery.makeArray(data);
			data.unshift( event );
		}

		event.currentTarget = elem;

		// Trigger the event, it is assumed that "handle" is a function
		var handle = jQuery.data(elem, "handle");
		if ( handle )
			handle.apply( elem, data );

		// Handle triggering native .onfoo handlers (and on links since we don't call .click() for links)
		if ( (!elem[type] || (jQuery.nodeName(elem, 'a') && type == "click")) && elem["on"+type] && elem["on"+type].apply( elem, data ) === false )
			event.result = false;

		// Trigger the native events (except for clicks on links)
		if ( !bubbling && elem[type] && !event.isDefaultPrevented() && !(jQuery.nodeName(elem, 'a') && type == "click") ) {
			this.triggered = true;
			try {
				elem[ type ]();
			// prevent IE from throwing an error for some hidden elements
			} catch (e) {}
		}

		this.triggered = false;

		if ( !event.isPropagationStopped() ) {
			var parent = elem.parentNode || elem.ownerDocument;
			if ( parent )
				jQuery.event.trigger(event, data, parent, true);
		}
	},

	handle: function(event) {
		// returned undefined or false
		var all, handlers;

		event = arguments[0] = jQuery.event.fix( event || window.event );
		event.currentTarget = this;
		
		// Namespaced event handlers
		var namespaces = event.type.split(".");
		event.type = namespaces.shift();

		// Cache this now, all = true means, any handler
		all = !namespaces.length && !event.exclusive;
		
		var namespace = RegExp("(^|\\.)" + namespaces.slice().sort().join(".*\\.") + "(\\.|$)");

		handlers = ( jQuery.data(this, "events") || {} )[event.type];

		for ( var j in handlers ) {
			var handler = handlers[j];

			// Filter the functions by class
			if ( all || namespace.test(handler.type) ) {
				// Pass in a reference to the handler function itself
				// So that we can later remove it
				event.handler = handler;
				event.data = handler.data;

				var ret = handler.apply(this, arguments);

				if( ret !== undefined ){
					event.result = ret;
					if ( ret === false ) {
						event.preventDefault();
						event.stopPropagation();
					}
				}

				if( event.isImmediatePropagationStopped() )
					break;

			}
		}
	},

	props: "altKey attrChange attrName bubbles button cancelable charCode clientX clientY ctrlKey currentTarget data detail eventPhase fromElement handler keyCode metaKey newValue originalTarget pageX pageY prevValue relatedNode relatedTarget screenX screenY shiftKey srcElement target toElement view wheelDelta which".split(" "),

	fix: function(event) {
		if ( event[expando] )
			return event;

		// store a copy of the original event object
		// and "clone" to set read-only properties
		var originalEvent = event;
		event = jQuery.Event( originalEvent );

		for ( var i = this.props.length, prop; i; ){
			prop = this.props[ --i ];
			event[ prop ] = originalEvent[ prop ];
		}

		// Fix target property, if necessary
		if ( !event.target )
			event.target = event.srcElement || document; // Fixes #1925 where srcElement might not be defined either

		// check if target is a textnode (safari)
		if ( event.target.nodeType == 3 )
			event.target = event.target.parentNode;

		// Add relatedTarget, if necessary
		if ( !event.relatedTarget && event.fromElement )
			event.relatedTarget = event.fromElement == event.target ? event.toElement : event.fromElement;

		// Calculate pageX/Y if missing and clientX/Y available
		if ( event.pageX == null && event.clientX != null ) {
			var doc = document.documentElement, body = document.body;
			event.pageX = event.clientX + (doc && doc.scrollLeft || body && body.scrollLeft || 0) - (doc.clientLeft || 0);
			event.pageY = event.clientY + (doc && doc.scrollTop || body && body.scrollTop || 0) - (doc.clientTop || 0);
		}

		// Add which for key events
		if ( !event.which && ((event.charCode || event.charCode === 0) ? event.charCode : event.keyCode) )
			event.which = event.charCode || event.keyCode;

		// Add metaKey to non-Mac browsers (use ctrl for PC's and Meta for Macs)
		if ( !event.metaKey && event.ctrlKey )
			event.metaKey = event.ctrlKey;

		// Add which for click: 1 == left; 2 == middle; 3 == right
		// Note: button is not normalized, so don't use it
		if ( !event.which && event.button )
			event.which = (event.button & 1 ? 1 : ( event.button & 2 ? 3 : ( event.button & 4 ? 2 : 0 ) ));

		return event;
	},

	proxy: function( fn, proxy ){
		proxy = proxy || function(){ return fn.apply(this, arguments); };
		// Set the guid of unique handler to the same of original handler, so it can be removed
		proxy.guid = fn.guid = fn.guid || proxy.guid || this.guid++;
		// So proxy can be declared as an argument
		return proxy;
	},

	special: {
		ready: {
			// Make sure the ready event is setup
			setup: bindReady,
			teardown: function() {}
		}
	},
	
	specialAll: {
		live: {
			setup: function( selector, namespaces ){
				jQuery.event.add( this, namespaces[0], liveHandler );
			},
			teardown:  function( namespaces ){
				if ( namespaces.length ) {
					var remove = 0, name = RegExp("(^|\\.)" + namespaces[0] + "(\\.|$)");
					
					jQuery.each( (jQuery.data(this, "events").live || {}), function(){
						if ( name.test(this.type) )
							remove++;
					});
					
					if ( remove < 1 )
						jQuery.event.remove( this, namespaces[0], liveHandler );
				}
			}
		}
	}
};

jQuery.Event = function( src ){
	// Allow instantiation without the 'new' keyword
	if( !this.preventDefault )
		return new jQuery.Event(src);
	
	// Event object
	if( src && src.type ){
		this.originalEvent = src;
		this.type = src.type;
	// Event type
	}else
		this.type = src;

	// timeStamp is buggy for some events on Firefox(#3843)
	// So we won't rely on the native value
	this.timeStamp = now();
	
	// Mark it as fixed
	this[expando] = true;
};

function returnFalse(){
	return false;
}
function returnTrue(){
	return true;
}

// jQuery.Event is based on DOM3 Events as specified by the ECMAScript Language Binding
// http://www.w3.org/TR/2003/WD-DOM-Level-3-Events-20030331/ecma-script-binding.html
jQuery.Event.prototype = {
	preventDefault: function() {
		this.isDefaultPrevented = returnTrue;

		var e = this.originalEvent;
		if( !e )
			return;
		// if preventDefault exists run it on the original event
		if (e.preventDefault)
			e.preventDefault();
		// otherwise set the returnValue property of the original event to false (IE)
		e.returnValue = false;
	},
	stopPropagation: function() {
		this.isPropagationStopped = returnTrue;

		var e = this.originalEvent;
		if( !e )
			return;
		// if stopPropagation exists run it on the original event
		if (e.stopPropagation)
			e.stopPropagation();
		// otherwise set the cancelBubble property of the original event to true (IE)
		e.cancelBubble = true;
	},
	stopImmediatePropagation:function(){
		this.isImmediatePropagationStopped = returnTrue;
		this.stopPropagation();
	},
	isDefaultPrevented: returnFalse,
	isPropagationStopped: returnFalse,
	isImmediatePropagationStopped: returnFalse
};
// Checks if an event happened on an element within another element
// Used in jQuery.event.special.mouseenter and mouseleave handlers
var withinElement = function(event) {
	// Check if mouse(over|out) are still within the same parent element
	var parent = event.relatedTarget;
	// Traverse up the tree
	while ( parent && parent != this )
		try { parent = parent.parentNode; }
		catch(e) { parent = this; }
	
	if( parent != this ){
		// set the correct event type
		event.type = event.data;
		// handle event if we actually just moused on to a non sub-element
		jQuery.event.handle.apply( this, arguments );
	}
};
	
jQuery.each({ 
	mouseover: 'mouseenter', 
	mouseout: 'mouseleave'
}, function( orig, fix ){
	jQuery.event.special[ fix ] = {
		setup: function(){
			jQuery.event.add( this, orig, withinElement, fix );
		},
		teardown: function(){
			jQuery.event.remove( this, orig, withinElement );
		}
	};			   
});

jQuery.fn.extend({
	bind: function( type, data, fn ) {
		return type == "unload" ? this.one(type, data, fn) : this.each(function(){
			jQuery.event.add( this, type, fn || data, fn && data );
		});
	},

	one: function( type, data, fn ) {
		var one = jQuery.event.proxy( fn || data, function(event) {
			jQuery(this).unbind(event, one);
			return (fn || data).apply( this, arguments );
		});
		return this.each(function(){
			jQuery.event.add( this, type, one, fn && data);
		});
	},

	unbind: function( type, fn ) {
		return this.each(function(){
			jQuery.event.remove( this, type, fn );
		});
	},

	trigger: function( type, data ) {
		return this.each(function(){
			jQuery.event.trigger( type, data, this );
		});
	},

	triggerHandler: function( type, data ) {
		if( this[0] ){
			var event = jQuery.Event(type);
			event.preventDefault();
			event.stopPropagation();
			jQuery.event.trigger( event, data, this[0] );
			return event.result;
		}		
	},

	toggle: function( fn ) {
		// Save reference to arguments for access in closure
		var args = arguments, i = 1;

		// link all the functions, so any of them can unbind this click handler
		while( i < args.length )
			jQuery.event.proxy( fn, args[i++] );

		return this.click( jQuery.event.proxy( fn, function(event) {
			// Figure out which function to execute
			this.lastToggle = ( this.lastToggle || 0 ) % i;

			// Make sure that clicks stop
			event.preventDefault();

			// and execute the function
			return args[ this.lastToggle++ ].apply( this, arguments ) || false;
		}));
	},

	hover: function(fnOver, fnOut) {
		return this.mouseenter(fnOver).mouseleave(fnOut);
	},

	ready: function(fn) {
		// Attach the listeners
		bindReady();

		// If the DOM is already ready
		if ( jQuery.isReady )
			// Execute the function immediately
			fn.call( document, jQuery );

		// Otherwise, remember the function for later
		else
			// Add the function to the wait list
			jQuery.readyList.push( fn );

		return this;
	},
	
	live: function( type, fn ){
		var proxy = jQuery.event.proxy( fn );
		proxy.guid += this.selector + type;

		jQuery(document).bind( liveConvert(type, this.selector), this.selector, proxy );

		return this;
	},
	
	die: function( type, fn ){
		jQuery(document).unbind( liveConvert(type, this.selector), fn ? { guid: fn.guid + this.selector + type } : null );
		return this;
	}
});

function liveHandler( event ){
	var check = RegExp("(^|\\.)" + event.type + "(\\.|$)"),
		stop = true,
		elems = [];

	jQuery.each(jQuery.data(this, "events").live || [], function(i, fn){
		if ( check.test(fn.type) ) {
			var elem = jQuery(event.target).closest(fn.data)[0];
			if ( elem )
				elems.push({ elem: elem, fn: fn });
		}
	});

	elems.sort(function(a,b) {
		return jQuery.data(a.elem, "closest") - jQuery.data(b.elem, "closest");
	});
	
	jQuery.each(elems, function(){
		if ( this.fn.call(this.elem, event, this.fn.data) === false )
			return (stop = false);
	});

	return stop;
}

function liveConvert(type, selector){
	return ["live", type, selector.replace(/\./g, "`").replace(/ /g, "|")].join(".");
}

jQuery.extend({
	isReady: false,
	readyList: [],
	// Handle when the DOM is ready
	ready: function() {
		// Make sure that the DOM is not already loaded
		if ( !jQuery.isReady ) {
			// Remember that the DOM is ready
			jQuery.isReady = true;

			// If there are functions bound, to execute
			if ( jQuery.readyList ) {
				// Execute all of them
				jQuery.each( jQuery.readyList, function(){
					this.call( document, jQuery );
				});

				// Reset the list of functions
				jQuery.readyList = null;
			}

			// Trigger any bound ready events
			jQuery(document).triggerHandler("ready");
		}
	}
});

var readyBound = false;

function bindReady(){
	if ( readyBound ) return;
	readyBound = true;

	// Mozilla, Opera and webkit nightlies currently support this event
	if ( document.addEventListener ) {
		// Use the handy event callback
		document.addEventListener( "DOMContentLoaded", function(){
			document.removeEventListener( "DOMContentLoaded", arguments.callee, false );
			jQuery.ready();
		}, false );

	// If IE event model is used
	} else if ( document.attachEvent ) {
		// ensure firing before onload,
		// maybe late but safe also for iframes
		document.attachEvent("onreadystatechange", function(){
			if ( document.readyState === "complete" ) {
				document.detachEvent( "onreadystatechange", arguments.callee );
				jQuery.ready();
			}
		});

		// If IE and not an iframe
		// continually check to see if the document is ready
		if ( document.documentElement.doScroll && window == window.top ) (function(){
			if ( jQuery.isReady ) return;

			try {
				// If IE is used, use the trick by Diego Perini
				// http://javascript.nwbox.com/IEContentLoaded/
				document.documentElement.doScroll("left");
			} catch( error ) {
				setTimeout( arguments.callee, 0 );
				return;
			}

			// and execute any waiting functions
			jQuery.ready();
		})();
	}

	// A fallback to window.onload, that will always work
	jQuery.event.add( window, "load", jQuery.ready );
}

jQuery.each( ("blur,focus,load,resize,scroll,unload,click,dblclick," +
	"mousedown,mouseup,mousemove,mouseover,mouseout,mouseenter,mouseleave," +
	"change,select,submit,keydown,keypress,keyup,error").split(","), function(i, name){

	// Handle event binding
	jQuery.fn[name] = function(fn){
		return fn ? this.bind(name, fn) : this.trigger(name);
	};
});

// Prevent memory leaks in IE
// And prevent errors on refresh with events like mouseover in other browsers
// Window isn't included so as not to unbind existing unload events
jQuery( window ).bind( 'unload', function(){ 
	for ( var id in jQuery.cache )
		// Skip the window
		if ( id != 1 && jQuery.cache[ id ].handle )
			jQuery.event.remove( jQuery.cache[ id ].handle.elem );
}); 
(function(){

	jQuery.support = {};

	var root = document.documentElement,
		script = document.createElement("script"),
		div = document.createElement("div"),
		id = "script" + (new Date).getTime();

	div.style.display = "none";
	div.innerHTML = '   <link/><table></table><a href="/a" style="color:red;float:left;opacity:.5;">a</a><select><option>text</option></select><object><param/></object>';

	var all = div.getElementsByTagName("*"),
		a = div.getElementsByTagName("a")[0];

	// Can't get basic test support
	if ( !all || !all.length || !a ) {
		return;
	}

	jQuery.support = {
		// IE strips leading whitespace when .innerHTML is used
		leadingWhitespace: div.firstChild.nodeType == 3,
		
		// Make sure that tbody elements aren't automatically inserted
		// IE will insert them into empty tables
		tbody: !div.getElementsByTagName("tbody").length,
		
		// Make sure that you can get all elements in an <object> element
		// IE 7 always returns no results
		objectAll: !!div.getElementsByTagName("object")[0]
			.getElementsByTagName("*").length,
		
		// Make sure that link elements get serialized correctly by innerHTML
		// This requires a wrapper element in IE
		htmlSerialize: !!div.getElementsByTagName("link").length,
		
		// Get the style information from getAttribute
		// (IE uses .cssText insted)
		style: /red/.test( a.getAttribute("style") ),
		
		// Make sure that URLs aren't manipulated
		// (IE normalizes it by default)
		hrefNormalized: a.getAttribute("href") === "/a",
		
		// Make sure that element opacity exists
		// (IE uses filter instead)
		opacity: a.style.opacity === "0.5",
		
		// Verify style float existence
		// (IE uses styleFloat instead of cssFloat)
		cssFloat: !!a.style.cssFloat,

		// Will be defined later
		scriptEval: false,
		noCloneEvent: true,
		boxModel: null
	};
	
	script.type = "text/javascript";
	try {
		script.appendChild( document.createTextNode( "window." + id + "=1;" ) );
	} catch(e){}

	root.insertBefore( script, root.firstChild );
	
	// Make sure that the execution of code works by injecting a script
	// tag with appendChild/createTextNode
	// (IE doesn't support this, fails, and uses .text instead)
	if ( window[ id ] ) {
		jQuery.support.scriptEval = true;
		delete window[ id ];
	}

	root.removeChild( script );

	if ( div.attachEvent && div.fireEvent ) {
		div.attachEvent("onclick", function(){
			// Cloning a node shouldn't copy over any
			// bound event handlers (IE does this)
			jQuery.support.noCloneEvent = false;
			div.detachEvent("onclick", arguments.callee);
		});
		div.cloneNode(true).fireEvent("onclick");
	}

	// Figure out if the W3C box model works as expected
	// document.body must exist before we can do this
	jQuery(function(){
		var div = document.createElement("div");
		div.style.width = div.style.paddingLeft = "1px";

		document.body.appendChild( div );
		jQuery.boxModel = jQuery.support.boxModel = div.offsetWidth === 2;
		document.body.removeChild( div ).style.display = 'none';
	});
})();

var styleFloat = jQuery.support.cssFloat ? "cssFloat" : "styleFloat";

jQuery.props = {
	"for": "htmlFor",
	"class": "className",
	"float": styleFloat,
	cssFloat: styleFloat,
	styleFloat: styleFloat,
	readonly: "readOnly",
	maxlength: "maxLength",
	cellspacing: "cellSpacing",
	rowspan: "rowSpan",
	tabindex: "tabIndex"
};
jQuery.fn.extend({
	// Keep a copy of the old load
	_load: jQuery.fn.load,

	load: function( url, params, callback ) {
		if ( typeof url !== "string" )
			return this._load( url );

		var off = url.indexOf(" ");
		if ( off >= 0 ) {
			var selector = url.slice(off, url.length);
			url = url.slice(0, off);
		}

		// Default to a GET request
		var type = "GET";

		// If the second parameter was provided
		if ( params )
			// If it's a function
			if ( jQuery.isFunction( params ) ) {
				// We assume that it's the callback
				callback = params;
				params = null;

			// Otherwise, build a param string
			} else if( typeof params === "object" ) {
				params = jQuery.param( params );
				type = "POST";
			}

		var self = this;

		// Request the remote document
		jQuery.ajax({
			url: url,
			type: type,
			dataType: "html",
			data: params,
			complete: function(res, status){
				// If successful, inject the HTML into all the matched elements
				if ( status == "success" || status == "notmodified" )
					// See if a selector was specified
					self.html( selector ?
						// Create a dummy div to hold the results
						jQuery("<div/>")
							// inject the contents of the document in, removing the scripts
							// to avoid any 'Permission Denied' errors in IE
							.append(res.responseText.replace(/<script(.|\s)*?\/script>/g, ""))

							// Locate the specified elements
							.find(selector) :

						// If not, just inject the full result
						res.responseText );

				if( callback )
					self.each( callback, [res.responseText, status, res] );
			}
		});
		return this;
	},

	serialize: function() {
		return jQuery.param(this.serializeArray());
	},
	serializeArray: function() {
		return this.map(function(){
			return this.elements ? jQuery.makeArray(this.elements) : this;
		})
		.filter(function(){
			return this.name && !this.disabled &&
				(this.checked || /select|textarea/i.test(this.nodeName) ||
					/text|hidden|password|search/i.test(this.type));
		})
		.map(function(i, elem){
			var val = jQuery(this).val();
			return val == null ? null :
				jQuery.isArray(val) ?
					jQuery.map( val, function(val, i){
						return {name: elem.name, value: val};
					}) :
					{name: elem.name, value: val};
		}).get();
	}
});

// Attach a bunch of functions for handling common AJAX events
jQuery.each( "ajaxStart,ajaxStop,ajaxComplete,ajaxError,ajaxSuccess,ajaxSend".split(","), function(i,o){
	jQuery.fn[o] = function(f){
		return this.bind(o, f);
	};
});

var jsc = now();

jQuery.extend({
  
	get: function( url, data, callback, type ) {
		// shift arguments if data argument was ommited
		if ( jQuery.isFunction( data ) ) {
			callback = data;
			data = null;
		}

		return jQuery.ajax({
			type: "GET",
			url: url,
			data: data,
			success: callback,
			dataType: type
		});
	},

	getScript: function( url, callback ) {
		return jQuery.get(url, null, callback, "script");
	},

	getJSON: function( url, data, callback ) {
		return jQuery.get(url, data, callback, "jsonp");
	},

	post: function( url, data, callback, type ) {
		if ( jQuery.isFunction( data ) ) {
			callback = data;
			data = {};
		}

		return jQuery.ajax({
			type: "POST",
			url: url,
			data: data,
			success: callback,
			dataType: type
		});
	},

	ajaxSetup: function( settings ) {
		jQuery.extend( jQuery.ajaxSettings, settings );
	},

	ajaxSettings: {
		url: location.href,
		global: true,
		type: "GET",
		contentType: "application/x-www-form-urlencoded",
		processData: true,
		async: true,
		/*
		timeout: 0,
		data: null,
		username: null,
		password: null,
		*/
		// Create the request object; Microsoft failed to properly
		// implement the XMLHttpRequest in IE7, so we use the ActiveXObject when it is available
		// This function can be overriden by calling jQuery.ajaxSetup
		xhr:function(){
			return window.ActiveXObject ? new ActiveXObject("Microsoft.XMLHTTP") : new XMLHttpRequest();
		},
		accepts: {
			xml: "application/xml, text/xml",
			html: "text/html",
			script: "text/javascript, application/javascript",
			json: "application/json, text/javascript",
			text: "text/plain",
			_default: "*/*"
		}
	},

	// Last-Modified header cache for next request
	lastModified: {},

	ajax: function( s ) {
		// Extend the settings, but re-extend 's' so that it can be
		// checked again later (in the test suite, specifically)
		s = jQuery.extend(true, s, jQuery.extend(true, {}, jQuery.ajaxSettings, s));

		var jsonp, jsre = /=\?(&|$)/g, status, data,
			type = s.type.toUpperCase();

		// convert data if not already a string
		if ( s.data && s.processData && typeof s.data !== "string" )
			s.data = jQuery.param(s.data);

		// Handle JSONP Parameter Callbacks
		if ( s.dataType == "jsonp" ) {
			if ( type == "GET" ) {
				if ( !s.url.match(jsre) )
					s.url += (s.url.match(/\?/) ? "&" : "?") + (s.jsonp || "callback") + "=?";
			} else if ( !s.data || !s.data.match(jsre) )
				s.data = (s.data ? s.data + "&" : "") + (s.jsonp || "callback") + "=?";
			s.dataType = "json";
		}

		// Build temporary JSONP function
		if ( s.dataType == "json" && (s.data && s.data.match(jsre) || s.url.match(jsre)) ) {
			jsonp = "jsonp" + jsc++;

			// Replace the =? sequence both in the query string and the data
			if ( s.data )
				s.data = (s.data + "").replace(jsre, "=" + jsonp + "$1");
			s.url = s.url.replace(jsre, "=" + jsonp + "$1");

			// We need to make sure
			// that a JSONP style response is executed properly
			s.dataType = "script";

			// Handle JSONP-style loading
			window[ jsonp ] = function(tmp){
				data = tmp;
				success();
				complete();
				// Garbage collect
				window[ jsonp ] = undefined;
				try{ delete window[ jsonp ]; } catch(e){}
				if ( head )
					head.removeChild( script );
			};
		}

		if ( s.dataType == "script" && s.cache == null )
			s.cache = false;

		if ( s.cache === false && type == "GET" ) {
			var ts = now();
			// try replacing _= if it is there
			var ret = s.url.replace(/(\?|&)_=.*?(&|$)/, "$1_=" + ts + "$2");
			// if nothing was replaced, add timestamp to the end
			s.url = ret + ((ret == s.url) ? (s.url.match(/\?/) ? "&" : "?") + "_=" + ts : "");
		}

		// If data is available, append data to url for get requests
		if ( s.data && type == "GET" ) {
			s.url += (s.url.match(/\?/) ? "&" : "?") + s.data;

			// IE likes to send both get and post data, prevent this
			s.data = null;
		}

		// Watch for a new set of requests
		if ( s.global && ! jQuery.active++ )
			jQuery.event.trigger( "ajaxStart" );

		// Matches an absolute URL, and saves the domain
		var parts = /^(\w+:)?\/\/([^\/?#]+)/.exec( s.url );

		// If we're requesting a remote document
		// and trying to load JSON or Script with a GET
		if ( s.dataType == "script" && type == "GET" && parts
			&& ( parts[1] && parts[1] != location.protocol || parts[2] != location.host )){

			var head = document.getElementsByTagName("head")[0];
			var script = document.createElement("script");
			script.src = s.url;
			if (s.scriptCharset)
				script.charset = s.scriptCharset;

			// Handle Script loading
			if ( !jsonp ) {
				var done = false;

				// Attach handlers for all browsers
				script.onload = script.onreadystatechange = function(){
					if ( !done && (!this.readyState ||
							this.readyState == "loaded" || this.readyState == "complete") ) {
						done = true;
						success();
						complete();

						// Handle memory leak in IE
						script.onload = script.onreadystatechange = null;
						head.removeChild( script );
					}
				};
			}

			head.appendChild(script);

			// We handle everything using the script element injection
			return undefined;
		}

		var requestDone = false;

		// Create the request object
		var xhr = s.xhr();

		// Open the socket
		// Passing null username, generates a login popup on Opera (#2865)
		if( s.username )
			xhr.open(type, s.url, s.async, s.username, s.password);
		else
			xhr.open(type, s.url, s.async);

		// Need an extra try/catch for cross domain requests in Firefox 3
		try {
			// Set the correct header, if data is being sent
			if ( s.data )
				xhr.setRequestHeader("Content-Type", s.contentType);

			// Set the If-Modified-Since header, if ifModified mode.
			if ( s.ifModified )
				xhr.setRequestHeader("If-Modified-Since",
					jQuery.lastModified[s.url] || "Thu, 01 Jan 1970 00:00:00 GMT" );

			// Set header so the called script knows that it's an XMLHttpRequest
			xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");

			// Set the Accepts header for the server, depending on the dataType
			xhr.setRequestHeader("Accept", s.dataType && s.accepts[ s.dataType ] ?
				s.accepts[ s.dataType ] + ", */*" :
				s.accepts._default );
		} catch(e){}

		// Allow custom headers/mimetypes and early abort
		if ( s.beforeSend && s.beforeSend(xhr, s) === false ) {
			// Handle the global AJAX counter
			if ( s.global && ! --jQuery.active )
				jQuery.event.trigger( "ajaxStop" );
			// close opended socket
			xhr.abort();
			return false;
		}

		if ( s.global )
			jQuery.event.trigger("ajaxSend", [xhr, s]);

		// Wait for a response to come back
		var onreadystatechange = function(isTimeout){
			// The request was aborted, clear the interval and decrement jQuery.active
			if (xhr.readyState == 0) {
				if (ival) {
					// clear poll interval
					clearInterval(ival);
					ival = null;
					// Handle the global AJAX counter
					if ( s.global && ! --jQuery.active )
						jQuery.event.trigger( "ajaxStop" );
				}
			// The transfer is complete and the data is available, or the request timed out
			} else if ( !requestDone && xhr && (xhr.readyState == 4 || isTimeout == "timeout") ) {
				requestDone = true;

				// clear poll interval
				if (ival) {
					clearInterval(ival);
					ival = null;
				}

				status = isTimeout == "timeout" ? "timeout" :
					!jQuery.httpSuccess( xhr ) ? "error" :
					s.ifModified && jQuery.httpNotModified( xhr, s.url ) ? "notmodified" :
					"success";

				if ( status == "success" ) {
					// Watch for, and catch, XML document parse errors
					try {
						// process the data (runs the xml through httpData regardless of callback)
						data = jQuery.httpData( xhr, s.dataType, s );
					} catch(e) {
						status = "parsererror";
					}
				}

				// Make sure that the request was successful or notmodified
				if ( status == "success" ) {
					// Cache Last-Modified header, if ifModified mode.
					var modRes;
					try {
						modRes = xhr.getResponseHeader("Last-Modified");
					} catch(e) {} // swallow exception thrown by FF if header is not available

					if ( s.ifModified && modRes )
						jQuery.lastModified[s.url] = modRes;

					// JSONP handles its own success callback
					if ( !jsonp )
						success();
				} else
					jQuery.handleError(s, xhr, status);

				// Fire the complete handlers
				complete();

				if ( isTimeout )
					xhr.abort();

				// Stop memory leaks
				if ( s.async )
					xhr = null;
			}
		};

		if ( s.async ) {
			// don't attach the handler to the request, just poll it instead
			var ival = setInterval(onreadystatechange, 13);

			// Timeout checker
			if ( s.timeout > 0 )
				setTimeout(function(){
					// Check to see if the request is still happening
					if ( xhr && !requestDone )
						onreadystatechange( "timeout" );
				}, s.timeout);
		}

		// Send the data
		try {
			xhr.send(s.data);
		} catch(e) {
			jQuery.handleError(s, xhr, null, e);
		}

		// firefox 1.5 doesn't fire statechange for sync requests
		if ( !s.async )
			onreadystatechange();

		function success(){
			// If a local callback was specified, fire it and pass it the data
			if ( s.success )
				s.success( data, status );

			// Fire the global callback
			if ( s.global )
				jQuery.event.trigger( "ajaxSuccess", [xhr, s] );
		}

		function complete(){
			// Process result
			if ( s.complete )
				s.complete(xhr, status);

			// The request was completed
			if ( s.global )
				jQuery.event.trigger( "ajaxComplete", [xhr, s] );

			// Handle the global AJAX counter
			if ( s.global && ! --jQuery.active )
				jQuery.event.trigger( "ajaxStop" );
		}

		// return XMLHttpRequest to allow aborting the request etc.
		return xhr;
	},

	handleError: function( s, xhr, status, e ) {
		// If a local callback was specified, fire it
		if ( s.error ) s.error( xhr, status, e );

		// Fire the global callback
		if ( s.global )
			jQuery.event.trigger( "ajaxError", [xhr, s, e] );
	},

	// Counter for holding the number of active queries
	active: 0,

	// Determines if an XMLHttpRequest was successful or not
	httpSuccess: function( xhr ) {
		try {
			// IE error sometimes returns 1223 when it should be 204 so treat it as success, see #1450
			return !xhr.status && location.protocol == "file:" ||
				( xhr.status >= 200 && xhr.status < 300 ) || xhr.status == 304 || xhr.status == 1223;
		} catch(e){}
		return false;
	},

	// Determines if an XMLHttpRequest returns NotModified
	httpNotModified: function( xhr, url ) {
		try {
			var xhrRes = xhr.getResponseHeader("Last-Modified");

			// Firefox always returns 200. check Last-Modified date
			return xhr.status == 304 || xhrRes == jQuery.lastModified[url];
		} catch(e){}
		return false;
	},

	httpData: function( xhr, type, s ) {
		var ct = xhr.getResponseHeader("content-type"),
			xml = type == "xml" || !type && ct && ct.indexOf("xml") >= 0,
			data = xml ? xhr.responseXML : xhr.responseText;

		if ( xml && data.documentElement.tagName == "parsererror" )
			throw "parsererror";
			
		// Allow a pre-filtering function to sanitize the response
		// s != null is checked to keep backwards compatibility
		if( s && s.dataFilter )
			data = s.dataFilter( data, type );

		// The filter can actually parse the response
		if( typeof data === "string" ){

			// If the type is "script", eval it in global context
			if ( type == "script" )
				jQuery.globalEval( data );

			// Get the JavaScript object, if JSON is used.
			if ( type == "json" )
				data = window["eval"]("(" + data + ")");
		}
		
		return data;
	},

	// Serialize an array of form elements or a set of
	// key/values into a query string
	param: function( a ) {
		var s = [ ];

		function add( key, value ){
			s[ s.length ] = encodeURIComponent(key) + '=' + encodeURIComponent(value);
		};

		// If an array was passed in, assume that it is an array
		// of form elements
		if ( jQuery.isArray(a) || a.jquery )
			// Serialize the form elements
			jQuery.each( a, function(){
				add( this.name, this.value );
			});

		// Otherwise, assume that it's an object of key/value pairs
		else
			// Serialize the key/values
			for ( var j in a )
				// If the value is an array then the key names need to be repeated
				if ( jQuery.isArray(a[j]) )
					jQuery.each( a[j], function(){
						add( j, this );
					});
				else
					add( j, jQuery.isFunction(a[j]) ? a[j]() : a[j] );

		// Return the resulting serialization
		return s.join("&").replace(/%20/g, "+");
	}

});
var elemdisplay = {},
	timerId,
	fxAttrs = [
		// height animations
		[ "height", "marginTop", "marginBottom", "paddingTop", "paddingBottom" ],
		// width animations
		[ "width", "marginLeft", "marginRight", "paddingLeft", "paddingRight" ],
		// opacity animations
		[ "opacity" ]
	];

function genFx( type, num ){
	var obj = {};
	jQuery.each( fxAttrs.concat.apply([], fxAttrs.slice(0,num)), function(){
		obj[ this ] = type;
	});
	return obj;
}

jQuery.fn.extend({
	show: function(speed,callback){
		if ( speed ) {
			return this.animate( genFx("show", 3), speed, callback);
		} else {
			for ( var i = 0, l = this.length; i < l; i++ ){
				var old = jQuery.data(this[i], "olddisplay");
				
				this[i].style.display = old || "";
				
				if ( jQuery.css(this[i], "display") === "none" ) {
					var tagName = this[i].tagName, display;
					
					if ( elemdisplay[ tagName ] ) {
						display = elemdisplay[ tagName ];
					} else {
						var elem = jQuery("<" + tagName + " />").appendTo("body");
						
						display = elem.css("display");
						if ( display === "none" )
							display = "block";
						
						elem.remove();
						
						elemdisplay[ tagName ] = display;
					}
					
					jQuery.data(this[i], "olddisplay", display);
				}
			}

			// Set the display of the elements in a second loop
			// to avoid the constant reflow
			for ( var i = 0, l = this.length; i < l; i++ ){
				this[i].style.display = jQuery.data(this[i], "olddisplay") || "";
			}
			
			return this;
		}
	},

	hide: function(speed,callback){
		if ( speed ) {
			return this.animate( genFx("hide", 3), speed, callback);
		} else {
			for ( var i = 0, l = this.length; i < l; i++ ){
				var old = jQuery.data(this[i], "olddisplay");
				if ( !old && old !== "none" )
					jQuery.data(this[i], "olddisplay", jQuery.css(this[i], "display"));
			}

			// Set the display of the elements in a second loop
			// to avoid the constant reflow
			for ( var i = 0, l = this.length; i < l; i++ ){
				this[i].style.display = "none";
			}

			return this;
		}
	},

	// Save the old toggle function
	_toggle: jQuery.fn.toggle,

	toggle: function( fn, fn2 ){
		var bool = typeof fn === "boolean";

		return jQuery.isFunction(fn) && jQuery.isFunction(fn2) ?
			this._toggle.apply( this, arguments ) :
			fn == null || bool ?
				this.each(function(){
					var state = bool ? fn : jQuery(this).is(":hidden");
					jQuery(this)[ state ? "show" : "hide" ]();
				}) :
				this.animate(genFx("toggle", 3), fn, fn2);
	},

	fadeTo: function(speed,to,callback){
		return this.animate({opacity: to}, speed, callback);
	},

	animate: function( prop, speed, easing, callback ) {
		var optall = jQuery.speed(speed, easing, callback);

		return this[ optall.queue === false ? "each" : "queue" ](function(){
		
			var opt = jQuery.extend({}, optall), p,
				hidden = this.nodeType == 1 && jQuery(this).is(":hidden"),
				self = this;
	
			for ( p in prop ) {
				if ( prop[p] == "hide" && hidden || prop[p] == "show" && !hidden )
					return opt.complete.call(this);

				if ( ( p == "height" || p == "width" ) && this.style ) {
					// Store display property
					opt.display = jQuery.css(this, "display");

					// Make sure that nothing sneaks out
					opt.overflow = this.style.overflow;
				}
			}

			if ( opt.overflow != null )
				this.style.overflow = "hidden";

			opt.curAnim = jQuery.extend({}, prop);

			jQuery.each( prop, function(name, val){
				var e = new jQuery.fx( self, opt, name );

				if ( /toggle|show|hide/.test(val) )
					e[ val == "toggle" ? hidden ? "show" : "hide" : val ]( prop );
				else {
					var parts = val.toString().match(/^([+-]=)?([\d+-.]+)(.*)$/),
						start = e.cur(true) || 0;

					if ( parts ) {
						var end = parseFloat(parts[2]),
							unit = parts[3] || "px";

						// We need to compute starting value
						if ( unit != "px" ) {
							self.style[ name ] = (end || 1) + unit;
							start = ((end || 1) / e.cur(true)) * start;
							self.style[ name ] = start + unit;
						}

						// If a +=/-= token was provided, we're doing a relative animation
						if ( parts[1] )
							end = ((parts[1] == "-=" ? -1 : 1) * end) + start;

						e.custom( start, end, unit );
					} else
						e.custom( start, val, "" );
				}
			});

			// For JS strict compliance
			return true;
		});
	},

	stop: function(clearQueue, gotoEnd){
		var timers = jQuery.timers;

		if (clearQueue)
			this.queue([]);

		this.each(function(){
			// go in reverse order so anything added to the queue during the loop is ignored
			for ( var i = timers.length - 1; i >= 0; i-- )
				if ( timers[i].elem == this ) {
					if (gotoEnd)
						// force the next step to be the last
						timers[i](true);
					timers.splice(i, 1);
				}
		});

		// start the next in the queue if the last step wasn't forced
		if (!gotoEnd)
			this.dequeue();

		return this;
	}

});

// Generate shortcuts for custom animations
jQuery.each({
	slideDown: genFx("show", 1),
	slideUp: genFx("hide", 1),
	slideToggle: genFx("toggle", 1),
	fadeIn: { opacity: "show" },
	fadeOut: { opacity: "hide" }
}, function( name, props ){
	jQuery.fn[ name ] = function( speed, callback ){
		return this.animate( props, speed, callback );
	};
});

jQuery.extend({

	speed: function(speed, easing, fn) {
		var opt = typeof speed === "object" ? speed : {
			complete: fn || !fn && easing ||
				jQuery.isFunction( speed ) && speed,
			duration: speed,
			easing: fn && easing || easing && !jQuery.isFunction(easing) && easing
		};

		opt.duration = jQuery.fx.off ? 0 : typeof opt.duration === "number" ? opt.duration :
			jQuery.fx.speeds[opt.duration] || jQuery.fx.speeds._default;

		// Queueing
		opt.old = opt.complete;
		opt.complete = function(){
			if ( opt.queue !== false )
				jQuery(this).dequeue();
			if ( jQuery.isFunction( opt.old ) )
				opt.old.call( this );
		};

		return opt;
	},

	easing: {
		linear: function( p, n, firstNum, diff ) {
			return firstNum + diff * p;
		},
		swing: function( p, n, firstNum, diff ) {
			return ((-Math.cos(p*Math.PI)/2) + 0.5) * diff + firstNum;
		}
	},

	timers: [],

	fx: function( elem, options, prop ){
		this.options = options;
		this.elem = elem;
		this.prop = prop;

		if ( !options.orig )
			options.orig = {};
	}

});

jQuery.fx.prototype = {

	// Simple function for setting a style value
	update: function(){
		if ( this.options.step )
			this.options.step.call( this.elem, this.now, this );

		(jQuery.fx.step[this.prop] || jQuery.fx.step._default)( this );

		// Set display property to block for height/width animations
		if ( ( this.prop == "height" || this.prop == "width" ) && this.elem.style )
			this.elem.style.display = "block";
	},

	// Get the current size
	cur: function(force){
		if ( this.elem[this.prop] != null && (!this.elem.style || this.elem.style[this.prop] == null) )
			return this.elem[ this.prop ];

		var r = parseFloat(jQuery.css(this.elem, this.prop, force));
		return r && r > -10000 ? r : parseFloat(jQuery.curCSS(this.elem, this.prop)) || 0;
	},

	// Start an animation from one number to another
	custom: function(from, to, unit){
		this.startTime = now();
		this.start = from;
		this.end = to;
		this.unit = unit || this.unit || "px";
		this.now = this.start;
		this.pos = this.state = 0;

		var self = this;
		function t(gotoEnd){
			return self.step(gotoEnd);
		}

		t.elem = this.elem;

		if ( t() && jQuery.timers.push(t) && !timerId ) {
			timerId = setInterval(function(){
				var timers = jQuery.timers;

				for ( var i = 0; i < timers.length; i++ )
					if ( !timers[i]() )
						timers.splice(i--, 1);

				if ( !timers.length ) {
					clearInterval( timerId );
					timerId = undefined;
				}
			}, 13);
		}
	},

	// Simple 'show' function
	show: function(){
		// Remember where we started, so that we can go back to it later
		this.options.orig[this.prop] = jQuery.attr( this.elem.style, this.prop );
		this.options.show = true;

		// Begin the animation
		// Make sure that we start at a small width/height to avoid any
		// flash of content
		this.custom(this.prop == "width" || this.prop == "height" ? 1 : 0, this.cur());

		// Start by showing the element
		jQuery(this.elem).show();
	},

	// Simple 'hide' function
	hide: function(){
		// Remember where we started, so that we can go back to it later
		this.options.orig[this.prop] = jQuery.attr( this.elem.style, this.prop );
		this.options.hide = true;

		// Begin the animation
		this.custom(this.cur(), 0);
	},

	// Each step of an animation
	step: function(gotoEnd){
		var t = now();

		if ( gotoEnd || t >= this.options.duration + this.startTime ) {
			this.now = this.end;
			this.pos = this.state = 1;
			this.update();

			this.options.curAnim[ this.prop ] = true;

			var done = true;
			for ( var i in this.options.curAnim )
				if ( this.options.curAnim[i] !== true )
					done = false;

			if ( done ) {
				if ( this.options.display != null ) {
					// Reset the overflow
					this.elem.style.overflow = this.options.overflow;

					// Reset the display
					this.elem.style.display = this.options.display;
					if ( jQuery.css(this.elem, "display") == "none" )
						this.elem.style.display = "block";
				}

				// Hide the element if the "hide" operation was done
				if ( this.options.hide )
					jQuery(this.elem).hide();

				// Reset the properties, if the item has been hidden or shown
				if ( this.options.hide || this.options.show )
					for ( var p in this.options.curAnim )
						jQuery.attr(this.elem.style, p, this.options.orig[p]);
					
				// Execute the complete function
				this.options.complete.call( this.elem );
			}

			return false;
		} else {
			var n = t - this.startTime;
			this.state = n / this.options.duration;

			// Perform the easing function, defaults to swing
			this.pos = jQuery.easing[this.options.easing || (jQuery.easing.swing ? "swing" : "linear")](this.state, n, 0, 1, this.options.duration);
			this.now = this.start + ((this.end - this.start) * this.pos);

			// Perform the next step of the animation
			this.update();
		}

		return true;
	}

};

jQuery.extend( jQuery.fx, {
	speeds:{
		slow: 600,
 		fast: 200,
 		// Default speed
 		_default: 400
	},
	step: {

		opacity: function(fx){
			jQuery.attr(fx.elem.style, "opacity", fx.now);
		},

		_default: function(fx){
			if ( fx.elem.style && fx.elem.style[ fx.prop ] != null )
				fx.elem.style[ fx.prop ] = fx.now + fx.unit;
			else
				fx.elem[ fx.prop ] = fx.now;
		}
	}
});
if ( document.documentElement["getBoundingClientRect"] )
	jQuery.fn.offset = function() {
		if ( !this[0] ) return { top: 0, left: 0 };
		if ( this[0] === this[0].ownerDocument.body ) return jQuery.offset.bodyOffset( this[0] );
		var box  = this[0].getBoundingClientRect(), doc = this[0].ownerDocument, body = doc.body, docElem = doc.documentElement,
			clientTop = docElem.clientTop || body.clientTop || 0, clientLeft = docElem.clientLeft || body.clientLeft || 0,
			top  = box.top  + (self.pageYOffset || jQuery.boxModel && docElem.scrollTop  || body.scrollTop ) - clientTop,
			left = box.left + (self.pageXOffset || jQuery.boxModel && docElem.scrollLeft || body.scrollLeft) - clientLeft;
		return { top: top, left: left };
	};
else 
	jQuery.fn.offset = function() {
		if ( !this[0] ) return { top: 0, left: 0 };
		if ( this[0] === this[0].ownerDocument.body ) return jQuery.offset.bodyOffset( this[0] );
		jQuery.offset.initialized || jQuery.offset.initialize();

		var elem = this[0], offsetParent = elem.offsetParent, prevOffsetParent = elem,
			doc = elem.ownerDocument, computedStyle, docElem = doc.documentElement,
			body = doc.body, defaultView = doc.defaultView,
			prevComputedStyle = defaultView.getComputedStyle(elem, null),
			top = elem.offsetTop, left = elem.offsetLeft;

		while ( (elem = elem.parentNode) && elem !== body && elem !== docElem ) {
			computedStyle = defaultView.getComputedStyle(elem, null);
			top -= elem.scrollTop, left -= elem.scrollLeft;
			if ( elem === offsetParent ) {
				top += elem.offsetTop, left += elem.offsetLeft;
				if ( jQuery.offset.doesNotAddBorder && !(jQuery.offset.doesAddBorderForTableAndCells && /^t(able|d|h)$/i.test(elem.tagName)) )
					top  += parseInt( computedStyle.borderTopWidth,  10) || 0,
					left += parseInt( computedStyle.borderLeftWidth, 10) || 0;
				prevOffsetParent = offsetParent, offsetParent = elem.offsetParent;
			}
			if ( jQuery.offset.subtractsBorderForOverflowNotVisible && computedStyle.overflow !== "visible" )
				top  += parseInt( computedStyle.borderTopWidth,  10) || 0,
				left += parseInt( computedStyle.borderLeftWidth, 10) || 0;
			prevComputedStyle = computedStyle;
		}

		if ( prevComputedStyle.position === "relative" || prevComputedStyle.position === "static" )
			top  += body.offsetTop,
			left += body.offsetLeft;

		if ( prevComputedStyle.position === "fixed" )
			top  += Math.max(docElem.scrollTop, body.scrollTop),
			left += Math.max(docElem.scrollLeft, body.scrollLeft);

		return { top: top, left: left };
	};

jQuery.offset = {
	initialize: function() {
		if ( this.initialized ) return;
		var body = document.body, container = document.createElement('div'), innerDiv, checkDiv, table, td, rules, prop, bodyMarginTop = body.style.marginTop,
			html = '<div style="position:absolute;top:0;left:0;margin:0;border:5px solid #000;padding:0;width:1px;height:1px;"><div></div></div><table style="position:absolute;top:0;left:0;margin:0;border:5px solid #000;padding:0;width:1px;height:1px;" cellpadding="0" cellspacing="0"><tr><td></td></tr></table>';

		rules = { position: 'absolute', top: 0, left: 0, margin: 0, border: 0, width: '1px', height: '1px', visibility: 'hidden' };
		for ( prop in rules ) container.style[prop] = rules[prop];

		container.innerHTML = html;
		body.insertBefore(container, body.firstChild);
		innerDiv = container.firstChild, checkDiv = innerDiv.firstChild, td = innerDiv.nextSibling.firstChild.firstChild;

		this.doesNotAddBorder = (checkDiv.offsetTop !== 5);
		this.doesAddBorderForTableAndCells = (td.offsetTop === 5);

		innerDiv.style.overflow = 'hidden', innerDiv.style.position = 'relative';
		this.subtractsBorderForOverflowNotVisible = (checkDiv.offsetTop === -5);

		body.style.marginTop = '1px';
		this.doesNotIncludeMarginInBodyOffset = (body.offsetTop === 0);
		body.style.marginTop = bodyMarginTop;

		body.removeChild(container);
		this.initialized = true;
	},

	bodyOffset: function(body) {
		jQuery.offset.initialized || jQuery.offset.initialize();
		var top = body.offsetTop, left = body.offsetLeft;
		if ( jQuery.offset.doesNotIncludeMarginInBodyOffset )
			top  += parseInt( jQuery.curCSS(body, 'marginTop',  true), 10 ) || 0,
			left += parseInt( jQuery.curCSS(body, 'marginLeft', true), 10 ) || 0;
		return { top: top, left: left };
	}
};


jQuery.fn.extend({
	position: function() {
		var left = 0, top = 0, results;

		if ( this[0] ) {
			// Get *real* offsetParent
			var offsetParent = this.offsetParent(),

			// Get correct offsets
			offset       = this.offset(),
			parentOffset = /^body|html$/i.test(offsetParent[0].tagName) ? { top: 0, left: 0 } : offsetParent.offset();

			// Subtract element margins
			// note: when an element has margin: auto the offsetLeft and marginLeft 
			// are the same in Safari causing offset.left to incorrectly be 0
			offset.top  -= num( this, 'marginTop'  );
			offset.left -= num( this, 'marginLeft' );

			// Add offsetParent borders
			parentOffset.top  += num( offsetParent, 'borderTopWidth'  );
			parentOffset.left += num( offsetParent, 'borderLeftWidth' );

			// Subtract the two offsets
			results = {
				top:  offset.top  - parentOffset.top,
				left: offset.left - parentOffset.left
			};
		}

		return results;
	},

	offsetParent: function() {
		var offsetParent = this[0].offsetParent || document.body;
		while ( offsetParent && (!/^body|html$/i.test(offsetParent.tagName) && jQuery.css(offsetParent, 'position') == 'static') )
			offsetParent = offsetParent.offsetParent;
		return jQuery(offsetParent);
	}
});


// Create scrollLeft and scrollTop methods
jQuery.each( ['Left', 'Top'], function(i, name) {
	var method = 'scroll' + name;
	
	jQuery.fn[ method ] = function(val) {
		if (!this[0]) return null;

		return val !== undefined ?

			// Set the scroll offset
			this.each(function() {
				this == window || this == document ?
					window.scrollTo(
						!i ? val : jQuery(window).scrollLeft(),
						 i ? val : jQuery(window).scrollTop()
					) :
					this[ method ] = val;
			}) :

			// Return the scroll offset
			this[0] == window || this[0] == document ?
				self[ i ? 'pageYOffset' : 'pageXOffset' ] ||
					jQuery.boxModel && document.documentElement[ method ] ||
					document.body[ method ] :
				this[0][ method ];
	};
});
// Create innerHeight, innerWidth, outerHeight and outerWidth methods
jQuery.each([ "Height", "Width" ], function(i, name){

	var tl = i ? "Left"  : "Top",  // top or left
		br = i ? "Right" : "Bottom", // bottom or right
		lower = name.toLowerCase();

	// innerHeight and innerWidth
	jQuery.fn["inner" + name] = function(){
		return this[0] ?
			jQuery.css( this[0], lower, false, "padding" ) :
			null;
	};

	// outerHeight and outerWidth
	jQuery.fn["outer" + name] = function(margin) {
		return this[0] ?
			jQuery.css( this[0], lower, false, margin ? "margin" : "border" ) :
			null;
	};
	
	var type = name.toLowerCase();

	jQuery.fn[ type ] = function( size ) {
		// Get window width or height
		return this[0] == window ?
			// Everyone else use document.documentElement or document.body depending on Quirks vs Standards mode
			document.compatMode == "CSS1Compat" && document.documentElement[ "client" + name ] ||
			document.body[ "client" + name ] :

			// Get document width or height
			this[0] == document ?
				// Either scroll[Width/Height] or offset[Width/Height], whichever is greater
				Math.max(
					document.documentElement["client" + name],
					document.body["scroll" + name], document.documentElement["scroll" + name],
					document.body["offset" + name], document.documentElement["offset" + name]
				) :

				// Get or set width or height on the element
				size === undefined ?
					// Get width or height on the element
					(this.length ? jQuery.css( this[0], type ) : null) :

					// Set the width or height on the element (default to pixels if value is unitless)
					this.css( type, typeof size === "string" ? size : size + "px" );
	};

});
})();
/*
    http://www.JSON.org/json2.js
    2009-06-29

    Public Domain.

    NO WARRANTY EXPRESSED OR IMPLIED. USE AT YOUR OWN RISK.

    See http://www.JSON.org/js.html

    This file creates a global JSON object containing two methods: stringify
    and parse.

        JSON.stringify(value, replacer, space)
            value       any JavaScript value, usually an object or array.

            replacer    an optional parameter that determines how object
                        values are stringified for objects. It can be a
                        function or an array of strings.

            space       an optional parameter that specifies the indentation
                        of nested structures. If it is omitted, the text will
                        be packed without extra whitespace. If it is a number,
                        it will specify the number of spaces to indent at each
                        level. If it is a string (such as '\t' or '&nbsp;'),
                        it contains the characters used to indent at each level.

            This method produces a JSON text from a JavaScript value.

            When an object value is found, if the object contains a toJSON
            method, its toJSON method will be called and the result will be
            stringified. A toJSON method does not serialize: it returns the
            value represented by the name/value pair that should be serialized,
            or undefined if nothing should be serialized. The toJSON method
            will be passed the key associated with the value, and this will be
            bound to the object holding the key.

            For example, this would serialize Dates as ISO strings.

                Date.prototype.toJSON = function (key) {
                    function f(n) {
                        // Format integers to have at least two digits.
                        return n < 10 ? '0' + n : n;
                    }

                    return this.getUTCFullYear()   + '-' +
                         f(this.getUTCMonth() + 1) + '-' +
                         f(this.getUTCDate())      + 'T' +
                         f(this.getUTCHours())     + ':' +
                         f(this.getUTCMinutes())   + ':' +
                         f(this.getUTCSeconds())   + 'Z';
                };

            You can provide an optional replacer method. It will be passed the
            key and value of each member, with this bound to the containing
            object. The value that is returned from your method will be
            serialized. If your method returns undefined, then the member will
            be excluded from the serialization.

            If the replacer parameter is an array of strings, then it will be
            used to select the members to be serialized. It filters the results
            such that only members with keys listed in the replacer array are
            stringified.

            Values that do not have JSON representations, such as undefined or
            functions, will not be serialized. Such values in objects will be
            dropped; in arrays they will be replaced with null. You can use
            a replacer function to replace those with JSON values.
            JSON.stringify(undefined) returns undefined.

            The optional space parameter produces a stringification of the
            value that is filled with line breaks and indentation to make it
            easier to read.

            If the space parameter is a non-empty string, then that string will
            be used for indentation. If the space parameter is a number, then
            the indentation will be that many spaces.

            Example:

            text = JSON.stringify(['e', {pluribus: 'unum'}]);
            // text is '["e",{"pluribus":"unum"}]'


            text = JSON.stringify(['e', {pluribus: 'unum'}], null, '\t');
            // text is '[\n\t"e",\n\t{\n\t\t"pluribus": "unum"\n\t}\n]'

            text = JSON.stringify([new Date()], function (key, value) {
                return this[key] instanceof Date ?
                    'Date(' + this[key] + ')' : value;
            });
            // text is '["Date(---current time---)"]'


        JSON.parse(text, reviver)
            This method parses a JSON text to produce an object or array.
            It can throw a SyntaxError exception.

            The optional reviver parameter is a function that can filter and
            transform the results. It receives each of the keys and values,
            and its return value is used instead of the original value.
            If it returns what it received, then the structure is not modified.
            If it returns undefined then the member is deleted.

            Example:

            // Parse the text. Values that look like ISO date strings will
            // be converted to Date objects.

            myData = JSON.parse(text, function (key, value) {
                var a;
                if (typeof value === 'string') {
                    a =
/^(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2}(?:\.\d*)?)Z$/.exec(value);
                    if (a) {
                        return new Date(Date.UTC(+a[1], +a[2] - 1, +a[3], +a[4],
                            +a[5], +a[6]));
                    }
                }
                return value;
            });

            myData = JSON.parse('["Date(09/09/2001)"]', function (key, value) {
                var d;
                if (typeof value === 'string' &&
                        value.slice(0, 5) === 'Date(' &&
                        value.slice(-1) === ')') {
                    d = new Date(value.slice(5, -1));
                    if (d) {
                        return d;
                    }
                }
                return value;
            });


    This is a reference implementation. You are free to copy, modify, or
    redistribute.

    This code should be minified before deployment.
    See http://javascript.crockford.com/jsmin.html

    USE YOUR OWN COPY. IT IS EXTREMELY UNWISE TO LOAD CODE FROM SERVERS YOU DO
    NOT CONTROL.
*/

/*jslint evil: true */

/*members "", "\b", "\t", "\n", "\f", "\r", "\"", JSON, "\\", apply,
    call, charCodeAt, getUTCDate, getUTCFullYear, getUTCHours,
    getUTCMinutes, getUTCMonth, getUTCSeconds, hasOwnProperty, join,
    lastIndex, length, parse, prototype, push, replace, slice, stringify,
    test, toJSON, toString, valueOf
*/

// Create a JSON object only if one does not already exist. We create the
// methods in a closure to avoid creating global variables.

var JSON = JSON || {};

(function () {

    function f(n) {
        // Format integers to have at least two digits.
        return n < 10 ? '0' + n : n;
    }

    if (typeof Date.prototype.toJSON !== 'function') {

        Date.prototype.toJSON = function (key) {

            return isFinite(this.valueOf()) ?
                   this.getUTCFullYear()   + '-' +
                 f(this.getUTCMonth() + 1) + '-' +
                 f(this.getUTCDate())      + 'T' +
                 f(this.getUTCHours())     + ':' +
                 f(this.getUTCMinutes())   + ':' +
                 f(this.getUTCSeconds())   + 'Z' : null;
        };

        String.prototype.toJSON =
        Number.prototype.toJSON =
        Boolean.prototype.toJSON = function (key) {
            return this.valueOf();
        };
    }

    var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        gap,
        indent,
        meta = {    // table of character substitutions
            '\b': '\\b',
            '\t': '\\t',
            '\n': '\\n',
            '\f': '\\f',
            '\r': '\\r',
            '"' : '\\"',
            '\\': '\\\\'
        },
        rep;


    function quote(string) {

// If the string contains no control characters, no quote characters, and no
// backslash characters, then we can safely slap some quotes around it.
// Otherwise we must also replace the offending characters with safe escape
// sequences.

        escapable.lastIndex = 0;
        return escapable.test(string) ?
            '"' + string.replace(escapable, function (a) {
                var c = meta[a];
                return typeof c === 'string' ? c :
                    '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
            }) + '"' :
            '"' + string + '"';
    }


    function str(key, holder) {

// Produce a string from holder[key].

        var i,          // The loop counter.
            k,          // The member key.
            v,          // The member value.
            length,
            mind = gap,
            partial,
            value = holder[key];

// If the value has a toJSON method, call it to obtain a replacement value.

        if (value && typeof value === 'object' &&
                typeof value.toJSON === 'function') {
            value = value.toJSON(key);
        }

// If we were called with a replacer function, then call the replacer to
// obtain a replacement value.

        if (typeof rep === 'function') {
            value = rep.call(holder, key, value);
        }

// What happens next depends on the value's type.

        switch (typeof value) {
        case 'string':
            return quote(value);

        case 'number':

// JSON numbers must be finite. Encode non-finite numbers as null.

            return isFinite(value) ? String(value) : 'null';

        case 'boolean':
        case 'null':

// If the value is a boolean or null, convert it to a string. Note:
// typeof null does not produce 'null'. The case is included here in
// the remote chance that this gets fixed someday.

            return String(value);

// If the type is 'object', we might be dealing with an object or an array or
// null.

        case 'object':

// Due to a specification blunder in ECMAScript, typeof null is 'object',
// so watch out for that case.

            if (!value) {
                return 'null';
            }

// Make an array to hold the partial results of stringifying this object value.

            gap += indent;
            partial = [];

// Is the value an array?

            if (Object.prototype.toString.apply(value) === '[object Array]') {

// The value is an array. Stringify every element. Use null as a placeholder
// for non-JSON values.

                length = value.length;
                for (i = 0; i < length; i += 1) {
                    partial[i] = str(i, value) || 'null';
                }

// Join all of the elements together, separated with commas, and wrap them in
// brackets.

                v = partial.length === 0 ? '[]' :
                    gap ? '[\n' + gap +
                            partial.join(',\n' + gap) + '\n' +
                                mind + ']' :
                          '[' + partial.join(',') + ']';
                gap = mind;
                return v;
            }

// If the replacer is an array, use it to select the members to be stringified.

            if (rep && typeof rep === 'object') {
                length = rep.length;
                for (i = 0; i < length; i += 1) {
                    k = rep[i];
                    if (typeof k === 'string') {
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            } else {

// Otherwise, iterate through all of the keys in the object.

                for (k in value) {
                    if (Object.hasOwnProperty.call(value, k)) {
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            }

// Join all of the member texts together, separated with commas,
// and wrap them in braces.

            v = partial.length === 0 ? '{}' :
                gap ? '{\n' + gap + partial.join(',\n' + gap) + '\n' +
                        mind + '}' : '{' + partial.join(',') + '}';
            gap = mind;
            return v;
        }
    }

// If the JSON object does not yet have a stringify method, give it one.

    if (typeof JSON.stringify !== 'function') {
        JSON.stringify = function (value, replacer, space) {

// The stringify method takes a value and an optional replacer, and an optional
// space parameter, and returns a JSON text. The replacer can be a function
// that can replace values, or an array of strings that will select the keys.
// A default replacer method can be provided. Use of the space parameter can
// produce text that is more easily readable.

            var i;
            gap = '';
            indent = '';

// If the space parameter is a number, make an indent string containing that
// many spaces.

            if (typeof space === 'number') {
                for (i = 0; i < space; i += 1) {
                    indent += ' ';
                }

// If the space parameter is a string, it will be used as the indent string.

            } else if (typeof space === 'string') {
                indent = space;
            }

// If there is a replacer, it must be a function or an array.
// Otherwise, throw an error.

            rep = replacer;
            if (replacer && typeof replacer !== 'function' &&
                    (typeof replacer !== 'object' ||
                     typeof replacer.length !== 'number')) {
                throw new Error('JSON.stringify');
            }

// Make a fake root object containing our value under the key of ''.
// Return the result of stringifying the value.

            return str('', {'': value});
        };
    }


// If the JSON object does not yet have a parse method, give it one.

    if (typeof JSON.parse !== 'function') {
        JSON.parse = function (text, reviver) {

// The parse method takes a text and an optional reviver function, and returns
// a JavaScript value if the text is a valid JSON text.

            var j;

            function walk(holder, key) {

// The walk method is used to recursively walk the resulting structure so
// that modifications can be made.

                var k, v, value = holder[key];
                if (value && typeof value === 'object') {
                    for (k in value) {
                        if (Object.hasOwnProperty.call(value, k)) {
                            v = walk(value, k);
                            if (v !== undefined) {
                                value[k] = v;
                            } else {
                                delete value[k];
                            }
                        }
                    }
                }
                return reviver.call(holder, key, value);
            }


// Parsing happens in four stages. In the first stage, we replace certain
// Unicode characters with escape sequences. JavaScript handles many characters
// incorrectly, either silently deleting them, or treating them as line endings.

            cx.lastIndex = 0;
            if (cx.test(text)) {
                text = text.replace(cx, function (a) {
                    return '\\u' +
                        ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
                });
            }

// In the second stage, we run the text against regular expressions that look
// for non-JSON patterns. We are especially concerned with '()' and 'new'
// because they can cause invocation, and '=' because it can cause mutation.
// But just to be safe, we want to reject all unexpected forms.

// We split the second stage into 4 regexp operations in order to work around
// crippling inefficiencies in IE's and Safari's regexp engines. First we
// replace the JSON backslash pairs with '@' (a non-JSON character). Second, we
// replace all simple value tokens with ']' characters. Third, we delete all
// open brackets that follow a colon or comma or that begin the text. Finally,
// we look to see that the remaining characters are only whitespace or ']' or
// ',' or ':' or '{' or '}'. If that is so, then the text is safe for eval.

            if (/^[\],:{}\s]*$/.
test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@').
replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']').
replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {

// In the third stage we use the eval function to compile the text into a
// JavaScript structure. The '{' operator is subject to a syntactic ambiguity
// in JavaScript: it can begin a block or an object literal. We wrap the text
// in parens to eliminate the ambiguity.

                j = eval('(' + text + ')');

// In the optional fourth stage, we recursively walk the new structure, passing
// each name/value pair to a reviver function for possible transformation.

                return typeof reviver === 'function' ?
                    walk({'': j}, '') : j;
            }

// If the text is not JSON parseable, then a SyntaxError is thrown.

            throw new SyntaxError('JSON.parse');
        };
    }
}());
//Declare the variables to be used in the REST URL as parameters.
document.write('<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>');
document.write('<script type="text/javascript">djConfig = { parseOnLoad: true }</script>');

//suresh
var oneMapGif = "http://www.onemap.sg/plugin/images/Red_glow.gif";
var host = "http://www.onemap.sg/";
//var geometryHost = "http://10.0.6.236:6080/";

//var host ="http://119.73.244.163/";
var _baseMapHost = "http://www.onemap.sg/ArcGIS/rest/services/";
var _baseMapURL = _baseMapHost + "basemap/MapServer";
var tileHost1 = "http://e1.onemap.sg/ArcGIS/rest/services/"
var tileHost2 = "http://e1.onemap.sg/ArcGIS/rest/services/"
var _baseMapURL1;
var _baseMapURL2;
//var _OneMapGlobalToken="";
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


//var token = "qo/s2TnSUmfLz+32CvLC4RMVkzEFYjxqyti1KhByvEacEdMWBpCuSSQ+IFRT84QjGPBCuz/cBom8PfSm3GjEsGc8PkdEEOEr";


//
var restResponse;
var parsedJSON;
var url;
var XYGraphicsLayer;

var restURL = "";

var OneMap;
var NewOneMap;
var _OneMapDiv;


function GetOneMap(divName, baseMap, optionalProps) {// by AM
	var i=1;
    try { var center = optionalProps.center; } catch (err) { }
    try { var level = optionalProps.level; } catch (err) { }
    try { var layer = optionalProps.layer; } catch (err) { }

if (baseMap.toUpperCase() == "SM") {
        _baseMapURL = _baseMapHost + "basemap" + "/MapServer";
	_baseMapURL1=tileHost1 + "basemap" + "/MapServer";
	_baseMapURL2=tileHost2 + "basemap" + "/MapServer";
    }
    else if (baseMap.toUpperCase() == "LL") {
        _baseMapURL = _baseMapHost + "LOT_VIEW" + "/MapServer";
	_baseMapURL1=tileHost1 + "LOT_VIEW" + "/MapServer";
	_baseMapURL2=tileHost2 + "LOT_VIEW" + "/MapServer";
    }
    else {
        _baseMapURL = _baseMapHost + baseMap + "/MapServer";
	_baseMapURL1=tileHost1 + baseMap + "/MapServer";
	_baseMapURL2=tileHost2 + baseMap + "/MapServer";

    }

    NewOneMap = this;
    _OneMapDiv = divName;
   dojo.require("esri.map");
	//dojo.require("esri.layers.tiled");

    esriConfig.defaults.map.slider = { left: "5px", top: "5px", width: null, height: "100px" };

	//var tiledMapServiceLayer =new  esri.layers.ArcGISTiledMapServiceLayer(_baseMapURL,{tileServers:[_baseMapURL1,_baseMapURL2]});

    dojo.addOnLoad(function() {
        var OneMap3;
        var startExtent = getExtentForLevelnCenter(center, level)
        var OneMap3 = new esri.Map(_OneMapDiv, { extent: startExtent });
        
	
////////////////added to get tiles from AWS//////////////////

dojo.declare("OM.CustomTileServiceLayer", esri.layers.TiledMapServiceLayer, {
        constructor: function() {
          this.spatialReference = new esri.SpatialReference({ wkid:3414 });
          this.initialExtent = (this.fullExtent = new esri.geometry.Extent(-4589.0529981345, 8065.64251572593, 96370.1129604966, 57234.9694430107, this.spatialReference));
          this.tileInfo = new esri.layers.TileInfo({
            "rows" : 256,
            "cols" : 256,
            "dpi" : 96,
            "format" : "JPEG",
            "origin" : {
              "x" : -5878011.89743302,
              "y" : 10172511.897433
            },
            "spatialReference" : {
              "wkid" : 3414
            },
            "lods" : [
              {"level" : 0, "resolution" : 76.4372195411057, "scale" : 288896},
              {"level" : 1, "resolution" : 38.2186097705529, "scale" : 144448},
              {"level" : 2, "resolution" : 19.1093048852764, "scale" : 72224},
              {"level" : 3, "resolution" : 9.55465244263822, "scale" : 36112},
              {"level" : 4, "resolution" : 4.77732622131911, "scale" : 18056},
              {"level" : 5, "resolution" : 2.38866311065955, "scale" : 9028},
              {"level" : 6, "resolution" : 1.19433155532978, "scale" : 4514},
              {"level" : 7, "resolution" : 0.597165777664889, "scale" : 2257},
              {"level" : 8, "resolution" : 0.298450596901194, "scale" : 1128}
            ]
          });

          this.loaded = true;
          this.onLoad(this);
        },

        getTileUrl: function(level, row, col) {
		//var URL="http://cf" + i + ".onemap.sg/BASEMAP/Layers/_alllayers/";
		
		var URL="http://e1.onemap.sg/BASEMAP/Layers/_alllayers/";

		//var URL="http://e" + i + ".onemap.sg/BASEMAP/Layers/_alllayers/";
		//i+=i;
		//if(i>2){
			//i=1;
		//}
          return URL +
                  "L" + dojo.string.pad(level, 2, '0') + "/" +
                  "R" + dojo.string.pad(row.toString(16), 8, '0') + "/" +
                  "C" + dojo.string.pad(col.toString(16), 8, '0') + "." +
                  "jpg";
        }
      });
////////////////added to get tiles from AWS/////////////////////////////

        if (baseMap.toUpperCase() == "SM")
        {
            OneMap3.addLayer(new OM.CustomTileServiceLayer());
        }
        else
        {
	    var tiledMapServiceLayer =new  esri.layers.ArcGISTiledMapServiceLayer(_baseMapURL,{tileServers:[_baseMapURL1,_baseMapURL2]});
            OneMap3.addLayer(tiledMapServiceLayer);
        }
////////////////added to get tiles from AWS/////////////////////////////

        //OneMap3.addLayer(tiledMapServiceLayer);

        XYGraphicsLayer = new esri.layers.GraphicsLayer();

        if ((layer != undefined) || (layer != null))
            OneMap3.addLayer(layer);
        NewOneMap.map = OneMap3;

        //custom methods
        NewOneMap.showLocation = showLocation;
        NewOneMap.mashupTheme = mashupTheme;
        NewOneMap.overlayKML = overlayKML;
        NewOneMap.clearGraphics = clearGraphics;
        NewOneMap.GetMultipleInfo = GetMultipleInfo;
        NewOneMap.onOneMapExtentChange = extentChangeAction;

        //dojo.connect(OneMap3, "onLoad", function() {

            var newdiv = document.createElement('div');

            var divIdName = 'OneMapLogo';
            newdiv.setAttribute('id', divIdName);
            //newdiv.setAttribute('onclick', function() { window.open('http://www.onemap.sg/home') });
 	    newdiv.setAttribute('onclick',"window.open('http://www.onemap.sg/home')");
            newdiv.setAttribute('style', "cursor:hand");
            newdiv.setAttribute('style', "cursor:pointer");
    
            document.getElementById(OneMap3.id+'_root').appendChild(newdiv);
            document.getElementById('OneMapLogo').innerHTML="<img src='http://t1.onemap.sg/api/js/imap_small_logo.gif'  style='vertical-align:bottom' />  <font style='font-family:Arial; font-size:0.7em; color:black;'><strong>&copy; 2014 OneMap</strong></font>";

            var t = $('#' + OneMap3.id+'_root').position().top;
            var l = $('#' + OneMap3.id+'_root').position().left;
            var ht = parseInt(document.getElementById(OneMap3.id).style.height.replace('px', ''))
            document.getElementById("OneMapLogo").style.top = (ht - 35) + 'px';
            //document.getElementById("OneMapLogo").style.left =  l + 'px';
            document.getElementById("OneMapLogo").style.position="absolute";
            document.getElementById("OneMapLogo").style.zIndex=100
          
       // });
    });

}


function getMultipleFactor(level) {

    var zoomLevel1 = 76.4372195411057
    var zoomLevel2 = 38.2186097705529
    var zoomLevel3 = 19.1093048852764
    var zoomLevel4 = 9.55465244263822
    var zoomLevel5 = 4.77732622131911
    var zoomLevel6 = 2.38866311065955
    var zoomLevel7 = 1.19433155532978
    var zoomLevel8 = 0.597165777664889
    var zoomLevel9 = 0.298450596901194
    var factor;
    factor = 200 * eval("zoomLevel" + level)
    return factor
}


function getExtentForPoint(x, y, buffer) {

    var xLocation1 = parseFloat(x) - buffer
    var yLocation1 = parseFloat(y) - buffer
    var xLocation2 = parseFloat(x) + buffer
    var yLocation2 = parseFloat(y) + buffer
    var startExtent = new esri.geometry.Extent({ xmin: xLocation1, ymin: yLocation1, xmax: xLocation2, ymax: yLocation2, spatialReference: { wkid: 3414} });
    return startExtent
}

function getExtentForLevelnCenter(center, level) {
    var buffer = 200;
    var xLocation
    var yLocation
    if ((center == null) || (center == undefined)) {
        center = "28968.103,33560.969";
        xLocation = center.split(",")[0]
        yLocation = center.split(",")[1]

        if ((level != null) || (level != undefined))
            buffer = getMultipleFactor(level);
        else
            buffer = getMultipleFactor(1);
    }
    else {
        //            var xLocation=center.split(",")[0]
        //            var yLocation=center.split(",")[1]

        if ((level != null) || (level != undefined))
            buffer = getMultipleFactor(level);
        else
            buffer = getMultipleFactor(1);
    }
    xLocation = center.split(",")[0]
    yLocation = center.split(",")[1]
    var extent = getExtentForPoint(xLocation, yLocation, buffer)
    return extent
}

function showLocation(xVal, yVal) {

    var OneMap2 = this.map;
    OneMap2.addLayer(XYGraphicsLayer);
    XYGraphicsLayer.clear();
    var xval = xVal;
    var yval = yVal;
    if (isNaN(xval) && isNaN(yval)) {
    }
    else {
        var XYsymbol = new esri.symbol.PictureMarkerSymbol(oneMapGif, 20, 20)
        var XYLocation = new esri.geometry.Point(xval, yval, new esri.SpatialReference({ wkid: 3414 }))
        var XYGraphic = new esri.Graphic(XYLocation, XYsymbol);
        XYGraphicsLayer.add(XYGraphic);
       

        var Srchpoint = new esri.geometry.Extent(XYLocation.x - 150, XYLocation.y - 150, XYLocation.x + 150, XYLocation.y + 150, new esri.SpatialReference({ wkid: 4326 }));
        OneMap2.setExtent(Srchpoint);
        
    }

}


//----------------extentChangeAction

function extentChangeAction(functionName) {

    var OneMap = this.map
    dojo.connect(OneMap, "onExtentChange", functionName)
}

//-------------- init xmlhttp -------------------------------------------------
var xmlhttp;
var xmlhttp2;
function GetXmlHttpObject() {
    if (window.XMLHttpRequest) {  // code for IE7+, Firefox, Chrome, Opera, Safari
        return new XMLHttpRequest();
    }
    if (window.ActiveXObject) {  // code for IE6, IE5
        return new ActiveXObject("Microsoft.XMLHTTP");
    } return null;
}


//-------------- Basic Search -------------------------------------------------

function BasicSearch() {

    this.token = "";
    this.searchVal = "";
    this.whereClause = "";
    this.otptFlds = "";
    this.returnGeom = "";
    this.rset = "";
    this.GetSearchResults = GetSearchResults;
}


function GetSearchResults(callback) {

    var obj = this;

    var token = obj.token;
    try { if (_OneMapGlobalToken != '') { token = _OneMapGlobalToken; } } catch (err) { }
    var searchval = obj.searchVal;
    var whereclause = obj.whereClause;
    var otptFlds = obj.otptFlds;
    var returngeom = obj.returnGeom;
    var rset = obj.rset;

    url = host + "API/services.svc/basicSearch?token=" + token

    if (searchval != "") {
        url = url + "&searchVal=" + searchval;
    }
    if (whereclause != "") {

        url = url + "&wc=" + whereclause;
    }
    if (otptFlds != "") {

        url = url + "&otptFlds=" + otptFlds;
    }
    if (returngeom != "") {

        url = url + "&returnGeom=" + returngeom;
    }
    if (rset != "") {

        url = url + "&rset=" + rset;
    }

    $.getJSON(url + "&callback=?", function(parsedJSON) {
        var outPutResults = new Object;
        outPutResults.nop = parsedJSON.SearchResults[0].PageCount;
        outPutResults.results = parsedJSON.SearchResults.splice(1, parsedJSON.SearchResults.length);
        if (outPutResults.results.length == 0) { outPutResults.results = "No results"; }
        callback(outPutResults);
    });
}
//BizQuery ---------------------------------------------------------------

function BizQuery() {
    this.token = "";
    this.dist = "";
    this.status ="";
    this.code = "";
    this.coord = "";
    this.GetNextResults= GetNextResults;
}

  function GetNextResults(callback){
    
    var obj = this;
    var token = obj.token;	
    var dist = obj.dist;
    var status = obj.status;
    var code = obj.code;
    var coord = obj.coord;
   // var rset = obj.rset;

	    url = host + "bizQuery/Service1.svc/getlist?token=" + token;
	    url = url + "&dist=" + dist;
          url = url + "&status=" + status;
	    url = url + "&code=" + code;
	    url = url + "&coord=" + coord;
	    $.getJSON(url + "&callback=?", function(parsedJSON) {
	        var outPutResults = new Object;
	        outPutResults.nop = parsedJSON.SearchResults[0].PageCount;
	        outPutResults.results = parsedJSON.SearchResults.splice(1, parsedJSON.SearchResults.length);
	        if (outPutResults.results.length == 0) { outPutResults.results = "No results"; }
	        callback(outPutResults);
	    });
    }
//end for BizQuery

//-------------- Event Details -------------------------------------------------
function EventData() {
    this.token = "";
    this.extent = "";
    this.rset = "";
    this.GetEventData = GetEventData;
}

var htmlString;

function GetEventData(callback) {
    var obj = this;
    var token = obj.token;
    try { if (_OneMapGlobalToken != '') { token = _OneMapGlobalToken; } } catch (err) { }

    var extent = obj.extent;
    var rset = obj.rset;

    url = host + "API/services.svc/getevents?token=" + token + "&extents=" + extent

    restURL = url;

    $.getJSON(url + "&callback=?", function(parsedJSON) {
        var outPutResults = new Object;
        outPutResults.nop = parsedJSON.SearchResults[0].PageCount;
        outPutResults.results = parsedJSON.SearchResults.splice(1, parsedJSON.SearchResults.length);
        if (outPutResults.results.length == 0) { outPutResults.results = "No results"; }
        callback(outPutResults);
    });

}


//-------------- Reverse Geocode -------------------------------------------------
function GetAddressInfo() {
    this.token = "";
    this.Postalcode = "";
    this.XY = "";
    this.GetAddress = GetAddress;
}

function GetAddress(callback) {
    var obj = this;
    var token = obj.token;
    try { if (_OneMapGlobalToken != '') { token = _OneMapGlobalToken; } } catch (err) { }

    var Postalcode = obj.Postalcode;
    var XY = obj.XY;
    url = host + "API/services.svc/revgeocode?token=" + token
     //url = host + "API/services.svc/revgeocode?token=Hfc6cEp4qybVIw4sagxi1yQbFIEzPUiN+yRkAV6/pODByLmmDo878pYNVSTE6ABE0YReToPoj2VOmM7btBdxP9DOuAzJs/Ip"
    if ((Postalcode == "") && (XY == "")) {
        var outPutResults = new Object;
        outPutResults.results = "No results";
        callback(outPutResults);
        return
    }

    if (XY != "") {
        url = url + "&location=" + XY;
    }
    else if (Postalcode != "") {
        url = url + "&Postalcode=" + Postalcode;
    }

    restURL = url;

    $.getJSON(url + "&callback=?", function (parsedJSON) {
        debugger;
        var outPutResults = new Object;
        outPutResults.results = parsedJSON.GeocodeInfo;
        if (outPutResults.results.length == 0) { outPutResults.results = "No results"; }
        callback(outPutResults);
    });
}

//-------------- Static Map -------------------------------------------------
function StaticMap() {
    this.token = "";
    this.bmap = "";
    this.center = "";
    this.lyrIds = "";
    this.size = "";
    this.level = "";
    this.points = "";
    this.lines = "";
    this.polys = "";
    this.weight = "";
    this.color = "";
    this.fillColor = "";
    this.GetStaticMap = GetStaticMap
}

function GetStaticMap() {

    var obj = this;
    var token = obj.token;
    try { if (_OneMapGlobalToken != '') { token = _OneMapGlobalToken; } } catch (err) { }
    var bmap = obj.bmap;
    var center = obj.center;
    var lyrids = obj.lyrIds;
    var size = obj.size;
    var level = obj.level;
    var points = obj.points;
    var lines = obj.lines;
    var polys = obj.polys;

    var weight = obj.weight;
    var color = obj.color;
    var fillcolor = obj.fillcolor;

    if (points == "" && lines == "" && polys == "") {debugger;

        url = host + "API/services.svc/getMap?token=" + token + "&bmap=" + bmap + "&size=" + size + "&level=" + level + "&center=" + center + "&lyrIds=" + lyrids;
    }
    else if (points != "" && lines == "" && polys == "") {
        url = host + "API/services.svc/getMap?token=" + token + "&bmap=" + bmap + "&size=" + size + "&level=" + level + "&center=" + center + "&lyrIds=" + lyrids + "&points=" + points;
    }
    else if (points == "" && lines != "" && polys == "") {
        url = host + "API/services.svc/getMap?token=" + token + "&bmap=" + bmap + "&size=" + size + "&level=" + level + "&center=" + center + "&lyrIds=" + lyrids + "&lines=" + lines;
    }
    else if (points == "" && lines == "" && polys != "") {
        url = host + "API/services.svc/getMap?token=" + token + "&bmap=" + bmap + "&size=" + size + "&level=" + level + "&center=" + center + "&lyrIds=" + lyrids + "&polys=" + polys;
    }
    else if (points != "" && lines != "" && polys != "") {
        url = host + "API/services.svc/getMap?token=" + token + "&bmap=" + bmap + "&size=" + size + "&level=" + level + "&center=" + center + "&lyrIds=" + lyrids + "&points=" + points + "&polys=" + polys + "&lines=" + lines;
    }
    else if (points != "" && lines != "" && polys == "") {
        url = host + "API/services.svc/getMap?token=" + token + "&bmap=" + bmap + "&size=" + size + "&level=" + level + "&center=" + center + "&lyrIds=" + lyrids + "&points=" + points + "&lines=" + lines;
    }
    else if (points != "" && lines == "" && polys != "") {
        url = host + "API/services.svc/getMap?token=" + token + "&bmap=" + bmap + "&size=" + size + "&level=" + level + "&center=" + center + "&lyrIds=" + lyrids + "&points=" + points + "&polys=" + polys;
    }
    else if (points == "" && lines != "" && polys != "") {
        url = host + "API/services.svc/getMap?token=" + token + "&bmap=" + bmap + "&size=" + size + "&level=" + level + "&center=" + center + "&lyrIds=" + lyrids + "&polys=" + polys + "&lines=" + lines;
    }


    //For the weight, color and fill Color.
    if (lines != "" && polys != "") {
        if (weight != "")
        { url = url + "&weight=" + weight; }
        if (color != "")
        { url = url + "&color=" + color; }
        if (fillcolor != "")
        { url = url + "&fillColor=" + fillcolor; }
    }

    restURL = url;

    return url;


}


//--------------- Map Route --------------------------------------

function Route() {
    this.token = "";
    this.routeStops = "";
    this.routeMode = "";
    this.avoidERP = "";
    this.routeOption = "";
    this.barriers = "";
    this.GetRoute = GetRoute;
}

function GetRoute(callback) {debugger;

    var token = this.token;
    try { if (_OneMapGlobalToken != '') { token = _OneMapGlobalToken; } } catch (err) { }
    var routestops = this.routeStops;
    var routemode = this.routeMode;
    var avoiderp = this.avoidERP;
    var routeOption = this.routeOption;
    var barriers = this.barriers;

    url = host + "API/services.svc/route/solve?token=" + token + "&routeStops=" + routestops + "&routeMode=" + routemode + "&avoidERP=" + avoiderp + "&routeOption=" + routeOption + "&barriers=" + barriers;

    restURL = url;


    $.getJSON(url + "&callback=?", function(parsedJSON) {
        var outPutResults = new Object;
        outPutResults.results = parsedJSON

	   try
	    {
	        if(outPutResults.results.directions[0].features.length > 0)
	        {
	            callback(outPutResults);
	            return;
	        }
       	    	else if (outPutResults.results.SearchResults[0].ErrorMessage != null)
       	    	{
	            if (outPutResults.results.SearchResults[0].ErrorMessage=="Error solving route")
	            {
	                outPutResults.results = "No results";
	            }
	            else if(outPutResults.results.SearchResults[0].ErrorMessage == "No result(s) found")
	            {
	                outPutResults.results = "No results";
	            }
	            else if (outPutResults.results.SearchResults[0].ErrorMessage==""){
	                outPutResults.results = "No results";
	            }
	            else if (outPutResults.results.SearchResults[0].ErrorMessage=="stops exceed")
	            {
	                outPutResults.results = "Stops more than nine";
	            }
	        }
        
            }
        catch(err)
            {
               //callback(outPutResults);
               alert("No result(s) found");
            }

        
        //if (outPutResults.results.length == 0) { outPutResults.results = "No results"; }
        callback(outPutResults);
    });
}



//-------------- Theme Search -------------------------------------------------

function ThemeSearch() {

    this.token = "";
    this.searchVal = "";
    this.returnGeom = "";
    this.otptFlds = "";
    this.rset = "";
    this.GetThemeSearchResults = GetThemeSearchResults;
}

function GetThemeSearchResults(callback) {


    var obj = this;
    var token = obj.token;
    try { if (_OneMapGlobalToken != '') { token = _OneMapGlobalToken; } } catch (err) { }
    var searchval = obj.searchVal;
    var otptFlds = obj.otptFlds;
    var returnGeom = obj.returnGeom;
    var rset = obj.rset;
    url = host;
    if (searchval != "") {
        url = url + "API/services.svc/themeSearch?token=" + token + "&searchVal=" + searchval;
    }
    //Search Value and page numnber
    if (otptFlds != "") {

        url = url + "&otptFlds=" + otptFlds;
    }

    if (returnGeom != "") {

        url = url + "&returnGeom=" + returnGeom;
    }

    if (rset != "") {

        url = url + "&rset=" + rset;
    }

    restURL = url;

    $.getJSON(url + "&callback=?", function(parsedJSON) {
        var outPutResults = new Object;

        outPutResults.nop = parsedJSON.SearchResults[0].PageCount;
        outPutResults.results = parsedJSON.SearchResults.splice(1, parsedJSON.SearchResults.length);

        if (outPutResults.results.length == 0) { outPutResults.results = "No results"; }
        callback(outPutResults);
    });

}



//-------------- Mashup API  -- WIth all params -------------------------------------------------
var layerData = "-";


function GetLayerInfoClass() {
    this.themeName = "";
    this.ExtracLayerInfo = ExtracLayerInfo;

}

function ExtracLayerInfo(callback) {
    layerName = this.themeName
    var url = host + "API/services.svc/layerinfoDM?layername=" + layerName;

    restURL = url;

    $.getJSON(url + "&callback=?", function(parsedJSON) {
        var outPutResults = new Object;
        var firstRecord = parsedJSON.LayerInfo[0]
        outPutResults.RelatedTabName = firstRecord.ADD_TABLE_NAM

        if (firstRecord.ADD_TABLE_NAM != "") {
            var callOutURL = host + "API/services.svc/" + firstRecord.ADD_TABLE_NAM + "/" + firstRecord.CALLOUTFIELDNAME + "/"

            outPutResults.calloutURL = callOutURL;
        }
        else {
            outPutResults.calloutURL = firstRecord.CALLOUTURL;
        }

        outPutResults.calloutFieldName = firstRecord.CALLOUTFIELDNAME
        outPutResults.Category = firstRecord.CATEGORY
        outPutResults.FeatureType = firstRecord.FEATTYPE;
        outPutResults.MinLevel = firstRecord.MINLEVEL
        outPutResults.MaxLevel = firstRecord.MAXLEVEL
        outPutResults.IconPath = firstRecord.IconPath
        outPutResults.AgencyName = firstRecord.AGENCY
        outPutResults.FieldNames = firstRecord.FIELD_NAM_T
        outPutResults.Icon = firstRecord.ICON_NAM_T
        outPutResults.MapTipFieldName = firstRecord.FIELD_NAM_T.split(",")[0];
        outPutResults.visibleFields = firstRecord.SHOWNATTRIBS;
        outPutResults.pointColour = firstRecord.COLOR_T;
        outPutResults.color = firstRecord.COLOR
        outPutResults.outlineColor = firstRecord.OUTLINECOLOR
        outPutResults.lineThickness = firstRecord.LINETHICKNESS

        callback(outPutResults)
    });
}


function MashupData() {
    this.token = "";
    this.themeName = "";
    this.outputFields = "";
    this.whereClause = "";
    this.extendedWhereClause = "";
    this.extent = "";
    this.map = "";
    this.graphicLayer = "";
    this.GetMashupData = GetMashupData;
    this.layerData = "";
    this.GetDataForCallout = GetDataForCallout;
    this.formatResultsEnhanced = formatResultsEnhanced;
}


function GetDataForCallout(graphic, wc, callback) {
    var mashupObj = this;
    var layerInfo = mashupObj.layerData;
    var calloutURL = layerInfo.calloutURL;

    if (calloutURL != "") {

        calloutURL = calloutURL + graphic.attributes[layerInfo.calloutFieldName];
        calloutURL = calloutURL + "?token=" + _OneMapGlobalToken
        if (wc != "") { calloutURL = calloutURL + "?wc=" + wc }

        $.getJSON(calloutURL + "&callback=?", function(parsedJSON) {
            var outPutResults = new Object;
            outPutResults = parsedJSON.SearchResults;
            callback(outPutResults);
        })
    }
    else {
        var outPutResults = new Array;

        outPutResults[0] = graphic.attributes;
        callback(outPutResults);
    }
}

function formatResultsEnhanced(resultObject) {
    //debugger;
    var nameVal = ""
    nameVal = nameVal + "<br/>"
    // to add Name on top
    for (var key in resultObject[0]) {
        switch (key) {
            case 'NAME':
                if (resultObject[0]["NAME"] != "") {
                    nameVal += "<strong>" + resultObject[0][key] + "</strong>" + "<br/>"
                    break;
                }
                else { break; }
        }
    }
    for (var key in resultObject[0]) {
        switch (key) {
            case 'NAME':
                if (resultObject[0]["NAME"] != "") {
                    break;
                }
                else { break; }
            case "PHOTOURL":
                if (resultObject[0]["PHOTOURL"] != "") {
                    break;
                }
                else { break; }
            case "ICON_NAME":
                if (resultObject[0]["ICON_NAME"] != "") {
                    break;
                }
                else { break; }
            case "XY":
                if (resultObject[0]["XY"] != "") {
                    break;
                }
                else { break; }
            case 'DESCRIPTION':
                if (resultObject[0]["DESCRIPTION"] != "") {
                    nameVal += resultObject[0]["DESCRIPTION"] + " "
                    break;
                }
                else { break; }
            case "HYPERLINK":
                if (resultObject[0]["HYPERLINK"] != "") {
                    nameVal += "<br/><a href=" + resultObject[0]["HYPERLINK"] + " target='_blank'>More Info</a>" + "<br/>"
                    break;
                }
                else { break; }
            case "ADDRESSSTREETNAME":
                if (resultObject[0]["ADDRESSSTREETNAME"] != "") {
                    nameVal += resultObject[0]["ADDRESSSTREETNAME"] + " "
                    break;
                }
                else { break; }
            case "ADDRESSBLOCKHOUSENUMBER":
                if (resultObject[0]["ADDRESSBLOCKHOUSENUMBER"] != "") {
                    nameVal += resultObject[0]["ADDRESSBLOCKHOUSENUMBER"] + " "
                    break;
                }
                else { break; }
            case "ADDRESSBUILDINGNAME":
                if (resultObject[0]["ADDRESSBUILDINGNAME"] != "") {
                    nameVal += resultObject[0]["ADDRESSBUILDINGNAME"] + " "
                    break;
                }
                else { break; }
            case "ADDRESSFLOORNUMBER":
                if (resultObject[0]["ADDRESSFLOORNUMBER"] != "") {
                    nameVal += resultObject[0]["ADDRESSFLOORNUMBER"] + " "
                    break;
                }
                else { break; }
            case "ADDRESSUNITNUMBER":
                if (resultObject[0]["ADDRESSUNITNUMBER"] != "") {
                    nameVal += resultObject[0]["ADDRESSUNITNUMBER"] + " "
                    break;
                }
                else { break; }
            case "ADDRESSPOSTALCODE":
                if (resultObject[0]["ADDRESSPOSTALCODE"] != "") {
                    nameVal += resultObject[0]["ADDRESSPOSTALCODE"] + " "
                    break;
                }
                else { break; }
            case "SYMBOLCOLOR":
                if (resultObject[0]["SYMBOLCOLOR"] != "") {
                    break;
                }
                else { break; }
            case "MAPTIP":
                if (resultObject[0]["MAPTIP"] != "") {
                    break;
                }
                else { break; }
            case "OBJECTID":
                if (resultObject[0]["OBJECTID"] != "") {
                    break;
                }
                else { break; }
            default:
                nameVal += resultObject[0][key] + "<br/>"
        }
    }
    // for photo to be on bottom 
    for (var key in resultObject[0]) {
        switch (key) {
            case "PHOTOURL":
                if (resultObject[0]["PHOTOURL"] != "") {
                    nameVal += "<img src=" + resultObject[0]["PHOTOURL"] + "></img>" + "<br/>"
                    break;
                }
                else { break; }
        }
    }
    return nameVal

}

function GetMashupData(callback) {
    var obj = this;
    var token = obj.token;
    try { if (_OneMapGlobalToken != '') { token = _OneMapGlobalToken; } else { _OneMapGlobalToken = token } } catch (err) { }
    var themename = obj.themeName;
    var otptFlds = obj.outputFields;
    var whereclause = obj.whereClause;
    var extent = obj.extent;
    var extendedWhereClause = obj.extendedWhereClause;
    var graphicLayer = obj.graphicLayer;
    var OneMap = obj.map

    url = host + "API/services.svc/mashupData";

    if (themename != "")
        url = url + "?token=" + token + "&themeName=" + themename;

    if (otptFlds != "")
        url = url + "&otptFlds=" + otptFlds;

    if (whereclause != "")
        url = url + "&wc=" + whereclause;

    if (extent != "")
        url = url + "&extents=" + extent;

    if (extendedWhereClause != "")
        url = url + "&exwc=" + extendedWhereClause;
    restURL = url;

    if ((obj.layerData == undefined) || (obj.layerData == "")) {
        var extractData = new GetLayerInfoClass()
        extractData.themeName = themename

        var extractedLayerData = extractData.ExtracLayerInfo(function(results) {
            layerData = results
        })
    }

    $.getJSON(url + "&callback=?", function(parsedJSON) {

        var outPutResults = new Object;
        if (parsedJSON.SrchResults.length == 1) {
            outPutResults.results = "No results";
            callback(outPutResults);
            return
        }

        outPutResults.count = parsedJSON.SrchResults[0].FeatCount;
        outPutResults.results = parsedJSON.SrchResults.splice(1, parsedJSON.SrchResults.length - 1);
        outPutResults.theme = themename;

        if ((obj.layerData == undefined) || (obj.layerData == "")) {
            obj.layerData = layerData;
        }
        else {
            layerData = obj.layerData;
        }

        var calloutFieldName = layerData.calloutFieldName
        var calloutFields = layerData.calloutFields;
        var calloutURL = layerData.calloutURL;
        var calloutUniqueFld = layerData.calloutUniqueFld;
        var iconPath = layerData.IconPath
        var featType = layerData.FeatureType
        var color = layerData.color

        outPutResults.calloutFieldName = calloutFieldName
        outPutResults.calloutURL = calloutURL
        outPutResults.calloutUniqueFld = calloutUniqueFld
        outPutResults.iconPath = iconPath
        outPutResults.featType = featType
        outPutResults.graphicLayer = graphicLayer;

        if ((layerData.color != undefined) && (layerData.color != undefined)) {
            outPutResults.color = hexToRGB(layerData.color);
        }
        else {
            outPutResults.color = '255,255,255';
        }

        if ((layerData.outlineColor != undefined) && (layerData.outlineColor != undefined)) {
            outPutResults.outlineColor = hexToRGB(layerData.outlineColor);
        }
        else {
            outPutResults.outlineColor = '255,255,255';
        }

        if ((layerData.lineThickness != undefined) && (layerData.lineThickness != undefined)) {
            outPutResults.lineThickness = layerData.lineThickness;
        }
        else {
            outPutResults.lineThickness = '1';
        }
        callback(outPutResults,themename);
    });

}

function GetMultipleInfo(url, results) {

    $.getJSON(url + "?token=" + _OneMapGlobalToken + "&callback=?", function(parsedJSON) {

        var outPutResults = new Object;
        outPutResults = parsedJSON.SearchResults;
        results(outPutResults);

    })

}

function cutHex(h) { return (h.charAt(0) == "#") ? h.substring(1, 7) : h }

function hexToRGB(hexValue) {
    var h = hexValue;
    var r = parseInt((cutHex(h)).substring(0, 2), 16);
    var g = parseInt((cutHex(h)).substring(2, 4), 16);
    var b = parseInt((cutHex(h)).substring(4, 6), 16);
    return (r + ',' + g + ',' + b)
}


//---------------------------------------KML ------------------------------- 









//-------------------------------------- Overlay KML End here------------------------------- 
var OneMapKML;
function overlayKML(kmlFilePath) {

    OneMapKML = this.map;
    var pointcoordinates = new String();
    var linecoordinates = new String();
    var isFirefox = false;
    var url = kmlFilePath;
    var xmlDoc;
    /*if (xmlDoc != null) return;

    var request = new XMLHttpRequest();
    request.open("GET", url, false);
    request.send(null);

    var xmlStr = request.responseText;
    xmlStr = xmlStr.replace("xmlns:xmlns", "xmlns");
    if (window.DOMParser) {
        parser = new DOMParser();
        xmlDoc = parser.parseFromString(xmlStr, "text/xml");
    }
    else // Internet Explorer
    {
        xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
        xmlDoc.async = false;
        xmlDoc.loadXML(xmlStr);
    }
    GetStringFromKML(xmlDoc);*/

    if (url.indexOf('160.96.187.80')>0){url=host+"api/Services.svc/overlayKML?path=" + url}
    if (url.indexOf('www.onemap.sg')>0){url=host+"api/Services.svc/overlayKML?path=" + url}
    
    if (xmlDoc != null) return;
    if (document.implementation && document.implementation.createDocument) {

        var request = new XMLHttpRequest();
        request.open("GET", url, false);
        request.send(null);
        xmlDoc = (new DOMParser()).parseFromString(request.responseText, "text/xml");

        GetStringFromKML(xmlDoc);       

    }
    else if (window.ActiveXObject) {
        xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
        xmlDoc.onreadystatechange = function() {
            if (xmlDoc.readyState == 4) { isFirefox = false; GetStringFromKML(xmlDoc); }
        };
    }
    else {
        var request = new XMLHttpRequest();
        request.open("GET", url, false);
        request.send(null);
        xmlDoc = (new DOMParser()).parseFromString(request.responseText, "text/xml");
        GetStringFromKML(xmlDoc);
    }
    xmlDoc.load(url);
    
}


function verify() {
    if (xmlDoc.readyState != 4) {
        return false;
    }
}
var RootNode;
function GetStringFromKML(xmlDoc) {

   
    var nodeList = xmlDoc.XmlNodeList;

    var strng = new String();
    var str = String;
    str = "";
    var linestr = String;
    linestr = "";
    var nameValue = String;
    nameValue = ""
    RootNode= xmlDoc.documentElement;
    var nodelist1 = xmlDoc.getElementsByTagName("Placemark")

    if (nodelist1.length == 0) {
        alert("Sorry.No feature found in KML file.")
        return
    }

    for (var i = 0; i < nodelist1.length; i++) {
        linestr = "";
        str = "";
        nameValue="";
     
        var nodelist2 = nodelist1[i].getElementsByTagName("Point")
        var nodelist3 = nodelist1[i].getElementsByTagName("LinearRing")
        var nodelist4 = nodelist1[i].getElementsByTagName("LineString")

        if (nodelist2.length != 0) {
            var PointNode = nodelist1[i].getElementsByTagName("Point")[0].getElementsByTagName("coordinates")[0].childNodes[0].nodeValue;
                
            var infoWindowContent=GetInfoWindowContent(nodelist1[i]);
            
            var iconName=GetIconNameFromKMLNode(nodelist1[i]);
     
            addPointToMap(PointNode,infoWindowContent,iconName)
            str = ""
            
            }

        if (nodelist3.length != 0) {
            var LineNode = nodelist1[i].getElementsByTagName("LinearRing")[0].getElementsByTagName("coordinates")[0].text;
            
            try{
                   var description = nodelist1[i].getElementsByTagName("description")[0].childNodes[0].nodeValue;
                   var checkforname =nodelist1[i].xml;
                   if (checkforname.indexOf("<name>")>=0)
                   {
                      var name =nodelist1[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
                       if (name!=null && name!="" && name!=undefined)
                        {
                            nameValue=name+"<br/>"
                        }
                        if (description!=null && description!="" && description!=undefined)
                        {
                            nameValue+=description+"<br/>";
                        }
                    }
                   else
                   {
                       nameValue=description;
                   }
              }
            catch (err) 
            {
         
            }
            
            linestr += LineNode + "~"
            splitDataandAddPoint(str, linestr, nameValue);

            }

        if (nodelist4.length != 0) {
            var LineNode = nodelist1[i].getElementsByTagName("LineString")[0].getElementsByTagName("coordinates")[0].text;
            //var nameValue = nodelist1[i].getElementsByTagName("description")[0].childNodes[0].nodeValue
           try{
                   var description = nodelist1[i].getElementsByTagName("description")[0].childNodes[0].nodeValue;
                   var checkforname =nodelist1[i].xml;
                   if (checkforname.indexOf("<name>")>=0)
                   {
                      var name =nodelist1[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
                       if (name!=null && name!="" && name!=undefined)
                        {
                            nameValue=name+"<br/>"
                        }
                        if (description!=null && description!="" && description!=undefined)
                        {
                            nameValue+=description+"<br/>";
                        }
                    }
                   else
                   {
                        if (description!=null && description!="" && description!=undefined)
                        {
                            nameValue=description;
                        }
                   }
              }
            catch (err) 
            {
         
            }
            linestr += LineNode + "~"
            splitDataandAddLines(linestr, nameValue);

            }
    }

}


function GetIconNameFromKMLNode(inputNode)
{
    var iconName=""
    try 
    {
         
         try
         {
          var stylelist = RootNode.getElementsByTagName("Style");
         
          for (var i = 0; i < stylelist.length; i++) 
          {
              var styleurl = inputNode.getElementsByTagName("styleUrl")[0].text;
              styleurl=styleurl.replace('#','');
              var styleid= RootNode.getElementsByTagName("Style")[i].attributes.getNamedItem("id").nodeValue;
              if (styleurl==styleid)
              {
                 iconName=RootNode.getElementsByTagName("Style")[i].getElementsByTagName("IconStyle")[0].getElementsByTagName("Icon")[0].getElementsByTagName("href")[0].childNodes[0].nodeValue;
                 try
                 {
                 iconName+=","+RootNode.getElementsByTagName("Style")[i].getElementsByTagName("IconStyle")[0].getElementsByTagName("Icon")[0].getElementsByTagName("width")[0].childNodes[0].nodeValue;
                 iconName+=","+ RootNode.getElementsByTagName("Style")[i].getElementsByTagName("IconStyle")[0].getElementsByTagName("Icon")[0].getElementsByTagName("height")[0].childNodes[0].nodeValue;
                 }
                 catch(err)
                 {
                 iconName+=",20,20";
                 }
                 break;
              }
          
          }
         
         }
         catch(err)
         {
         iconName=inputNode.getElementsByTagName("Style")[0].getElementsByTagName("IconStyle")[0].getElementsByTagName("Icon")[0].getElementsByTagName("href")[0].childNodes[0].nodeValue;
         try
          {
           iconName+=","+RootNode.getElementsByTagName("Style")[i].getElementsByTagName("IconStyle")[0].getElementsByTagName("Icon")[0].getElementsByTagName("width")[0].childNodes[0].nodeValue;
           iconName+=","+ RootNode.getElementsByTagName("Style")[i].getElementsByTagName("IconStyle")[0].getElementsByTagName("Icon")[0].getElementsByTagName("height")[0].childNodes[0].nodeValue;
          }
         catch(err)
         {
         iconName+=",20,20";
         }
         }
        
         
         
         
    }
    catch (err) 
    {
         iconName=""
    }
    return iconName
           
}

function GetInfoWindowContent(inputNode)

{

 
    var nValue = "";
    var nameValue="";
	var title="";
   /*--santhosh--*/
    try {
     
        nValue = inputNode.getElementsByTagName("name")[0].childNodes[0].nodeValue;
        if (nValue!=null && nValue!="" && nValue!=undefined)
        {
        title +=nValue + "</BR>"
        }
        else
        {
            nValue = inputNode.getElementsByTagName("name")[0].childNodes[0].text;
            if (nValue!=null && nValue!="" && nValue!=undefined)
            {
            title +=nValue + "</BR>"
        }
        
        }
    }
    catch (err) {
         
    }

    try {
    
        //nValue = inputNode.getElementsByTagName("description")[0].childNodes[0].nodeValue;
		nValue = inputNode.getElementsByTagName("description")[0].childNodes[0].childNodes[0].childNodes[0].childNodes[0].nodeValue;
        
        if (nValue!=null && nValue!="" && nValue!=undefined)
        {
           nameValue += nValue + "</BR>";
           
        }
        else
        {
            nValue = inputNode.getElementsByTagName("description")[0].childNodes[0].childNodes[0].childNodes[0].childNodes[0].text;
            
            if (nValue!=null && nValue!="" && nValue!=undefined)
            {
               nameValue += nValue + "</BR>";
            }
        
        }
        
    }
    catch (err) {
         
    }
    return title+"|"+nameValue;
                    
}


function splitDataandAddLines(lineCord, nameValue) {


    if (lineCord.length != 0) {

        addPolylineToMap(lineCord.split(' '), nameValue)
    }

}


function splitDataandAddPoint(xyCord, lineCord, nameValue) {



    if (xyCord.length != 0) {
        inputXY = xyCord;
        var strLen = inputXY.length;
        inputXY = inputXY.slice(0, strLen - 1);
        var coords = new Array();
        coords = inputXY.split("~")
        for (var i = 0; i < coords.length; i++) {
            var coords1 = coords[i]
            var crds = new Array();
            crds = coords1.split("|")
            var crd = crds[0]
            var crd1 = crds[1]
            var nV = crd1.slice(0, crd1.length - 1)
            var fnV = new Array();
            fnV = nV.split("^@")
            var fnVfinal = fnV[i]
            var xCord = crd.split(",")[0]
            var yCord = crd.split(",")[1]
            addPointToMap(xCord, yCord, fnVfinal)
        }
    }

    if (lineCord.length != 0) {

        addPolygonToMap(lineCord.split(' '), nameValue)
    }

}


function addPointToMap(XY,infoWindowContent,iconURL)
{


    var lon, lat;
    lon=XY.split(",")[0]
    lat=XY.split(",")[1]
    var pointSymbol;
    if (iconURL==""){
        pointSymbol = new esri.symbol.SimpleMarkerSymbol().setSize(10).setColor(new dojo.Color([255, 0, 0]));   
    }
    else 
    { 
        try
        {
        pointSymbol = new esri.symbol.PictureMarkerSymbol(iconURL.split(",")[0], iconURL.split(",")[1], iconURL.split(",")[2]);
        }
        catch(err)
        {
        pointSymbol = new esri.symbol.PictureMarkerSymbol(iconURL,20,20);
        }
        
    }
    
    gsvc = new esri.tasks.GeometryService(host + "ArcGIS/rest/services/Utilities/Geometry/GeometryServer");
    var geomPoint = new esri.geometry.Point(lon, lat, new esri.SpatialReference({ wkid: 4326 }));

    var graphic = new esri.Graphic(geomPoint, pointSymbol);
    var npr = new esri.SpatialReference({ wkid: 3414 });


    var infoTemplate1 = new esri.InfoTemplate();
    infoTemplate1.setTitle((infoWindowContent.split("|"))[0]);
    infoTemplate1.setContent("<div style='overflow-x:scroll;height:112px; width:200px'>" + (infoWindowContent.split("|"))[1] + "</div>");

    if (lon.split(".")[0] > 999) { 

        var graphic1 = new esri.Graphic(geomPoint, pointSymbol);

        graphic1.setInfoTemplate(infoTemplate1);
        OneMapKML.graphics.add(graphic1);
    }
    else {
        gsvc.project([geomPoint ], npr, function(featres) {
           var geomPoint1 = featres[0].geometry;
		var XYLocation = new esri.geometry.Point(featres[0].x, featres[0].y, new esri.SpatialReference({ wkid: 3414 }));

            var graphic1 = new esri.Graphic(XYLocation  , pointSymbol);
            graphic1.setInfoTemplate(infoTemplate1);
            OneMapKML.graphics.add(graphic1);
        });
    }
    

}

var tempPoint;

function addPolygonToMap(polyPath, nameValue) {


    var color = "255,0,0";
    var lineThickness = "1.2";
    var outlineColor = "0,0,255";

    gsvc = new esri.tasks.GeometryService(host + "ArcGIS/rest/services/Utilities/Geometry/GeometryServer");
    var npr = new esri.SpatialReference({ wkid: 3414 });
    var poly;

    var coordType = 0
    var lngraphic = new esri.Graphic();

    var point1;
    var pointSymbol = new esri.symbol.SimpleMarkerSymbol().setSize(10).setColor(new dojo.Color([255, 0, 0]));

    var symbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color(outlineColor.split(",")), lineThickness), new dojo.Color((color + ",0.5").split(",")));
    var pntArr = new Array;

    var factor = 1;
    //Added for other KML
    if (polyPath[0].split(",")[0]> 200)
    {
    coordType = 1
    }
    if (polyPath.length > 30) { factor = 2 }
    for (var l = 0; l < polyPath.length; l=l+factor) {
    
        point1 = new esri.geometry.Point(polyPath[l].split(",")[0].replace('~', ''), polyPath[l].split(",")[1].replace('~', ''), new esri.SpatialReference({ wkid: 4326 }))
        pntArr.push(point1)

    }

    var polygon = new esri.geometry.Polygon(new esri.SpatialReference({ wkid: 4326 }));
    polygon.addRing(pntArr);
    lngraphic.geometry = polygon;
    var infoTemplate1 = new esri.InfoTemplate();
    infoTemplate1.setTitle("KML Info");

    infoTemplate1.setContent("<div style='overflow-x:scroll;height:112px; width:242px'>" + nameValue + "</div>");

    //coordType = 0
    if (coordType == 1) {
         lngraphic.setSymbol(symbol)

        lngraphic.setInfoTemplate(infoTemplate1);
        OneMapKML.graphics.add(lngraphic);
    }
    else {

        gsvc.project([lngraphic], npr, function(features) {
            var geomPoint1 = features[0].geometry;
            var graphic1 = new esri.Graphic(geomPoint1, symbol);
            graphic1.setInfoTemplate(infoTemplate1);
            OneMapKML.graphics.add(graphic1);
        });
    }

}


function addPolylineToMap(polyPath, nameValue) {
    
  
    var color = "255,0,0";
    var lineThickness = "1.2";
    var outlineColor = "0,0,255";

    gsvc = new esri.tasks.GeometryService(host + "ArcGIS/rest/services/Utilities/Geometry/GeometryServer");
    var npr = new esri.SpatialReference({ wkid: 3414 });
    var poly;

    var coordType = 0
    var lngraphic = new esri.Graphic();

    var point1;
    //var pointSymbol = new esri.symbol.SimpleMarkerSymbol().setSize(10).setColor(new dojo.Color([255, 0, 0]));

    var symbol = new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_DASH, new dojo.Color([255,0,0]), 3); //new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color(color.split(",")), 1.1);

    var pntArr = new Array;

    var factor = 1;
     if (polyPath[0].split(",")[0]> 200)
    {
    coordType = 1
    }
    if (polyPath.length > 30) { factor = 2 }
    for (var l = 0; l < polyPath.length; l = l + factor) {
        point1 = new esri.geometry.Point(polyPath[l].split(",")[0].replace('~', ''), polyPath[l].split(",")[1].replace('~', ''), new esri.SpatialReference({ wkid: 4326 }))

        pntArr.push(point1)

    }

    var polygon = new esri.geometry.Polyline(new esri.SpatialReference({ wkid: 4326 }));
    polygon.addPath(pntArr);
    lngraphic.geometry = polygon;
    var infoTemplate1 = new esri.InfoTemplate();
    infoTemplate1.setTitle("KML Info");

    infoTemplate1.setContent("<div style='overflow-x:scroll;height:112px; width:242px'>" + nameValue + "</div>");

    //coordType = 0
    if (coordType == 1) {
        lngraphic.setSymbol(symbol)

        lngraphic.setInfoTemplate(infoTemplate1);
        OneMapKML.graphics.add(lngraphic);
    }
    else {

        gsvc.project([lngraphic], npr, function(features) {
            var geomPoint1 = features[0].geometry;
            var graphic1 = new esri.Graphic(geomPoint1, symbol);
            graphic1.setInfoTemplate(infoTemplate1);
            OneMapKML.graphics.add(graphic1);
        });
    }

}



function clearGraphics() {
    var OneMap = this.map;
    try {
        OneMap.graphics.clear();
    }
    catch (Error) {
    }

}


//-------------- mashupTheme-------------------------------------------------
var _OneMapCurrExtents = "";

function mashupDataOnExtentChnage(extent) {
    _OneMapCurrExtents = extent.xmin + "," + extent.ymin + "," + extent.xmax + "," + extent.ymax;

    var mashupOneMap = this
    for (var i = 0; i < mashupOneMap.graphicsLayerIds.length; i++) {
        var mashupObject = new MashupData();
        var themeName = mashupOneMap.graphicsLayerIds[i]
        mashupObject.token = _OneMapGlobalToken
        mashupObject.themeName = themeName;
        mashupObject.extent = _OneMapCurrExtents;
        var _OneMapThemeGraphicsLayer = mashupOneMap._layers[themeName];
        mashupObject.graphicLayer = _OneMapThemeGraphicsLayer;
        mashupObject.GetMashupData(function(mashupResults) {
            var results = mashupResults.results;
            if (results == "No results") {
                return
            }

            var featcount = mashupResults.count;
            var themeName = mashupResults.theme
            var calloutFields = mashupResults.calloutFields;
            var calloutURL = mashupResults.calloutURL;
            var calloutUniqueFld = mashupResults.calloutUniqueFld;
            var iconPath = mashupResults.iconPath
            var featType = mashupResults.featType;
            var color = mashupResults.color
            var outlineColor = mashupResults.outlineColor
            var lineThickness = mashupResults.lineThickness
            var themeGraphicsLayer_1 = mashupResults.graphicLayer

            themeGraphicsLayer_1.clear();

            if (results.length == 0) { return }

            for (var i = 0; i < results.length; i++) {
                if (calloutURL != "") {
                    calloutURL = calloutURL.replace("intranet.onemap.gov.sg", "www.onemap.sg")

                    var multipleDataURL = calloutURL + results[i][calloutUniqueFld.toUpperCase()]
                    var nameVal = "";
                    nameVal = "<iFrame src='" + multipleDataURL + "'></iFrame>"
                    infoTemplate1.setContent(nameVal);
                }
                else {
                    var infoTemplate1 = new esri.InfoTemplate();
                    infoTemplate1.setTitle(themeName);
                    var nameVal = "";
                    nameVal += "<strong>" + results[i]["NAME"] + "</strong>" + "<br/>"
                    nameVal += results[i]["DESCRIPTION"] + "<br/>"
                    nameVal += "<a href=" + results[i]["HYPERLINK"] + " target='_blank'>More Info</a>"
                    nameVal += "<img src=" + results[i]["PHOTOURL"] + "></img>" + "<br/>"
                    nameVal += results[i]["ADDRESSBLOCKHOUSENUMBER"] + " " + results[i]["ADDRESSSTREETNAME"] + " " + results[i]["ADDRESSBUILDINGNAME"] + " " + results[i]["ADDRESSFLOORNUMBER"] + " " + results[i]["ADDRESSUNITNUMBER"] + "<br/>"
                    nameVal += "Singapore " + results[i]["ADDRESSPOSTALCODE"] + "<br/>"
                    infoTemplate1.setContent(nameVal);
                }
                var graphic;
                if (featType.toUpperCase() == "LINE") {
                    graphic = generateLineGraphic(results[i].XY, color, lineThickness)
                }
                else if (featType.toUpperCase() == "POLYGON") {
                    graphic = generatePolygonGraphic(results[i].XY, color, outlineColor, lineThickness)
                }
                else if (featType.toUpperCase() == "POINT") {
                    graphic = generatePointGraphic(results[i].XY, results[i].ICON_NAME, iconPath)
                }

                graphic.setInfoTemplate(infoTemplate1);
                themeGraphicsLayer_1.add(graphic);
            }
        }
        );

    }
}

function mashupTheme(themeName, token) {
    if (themeName == "") { return }

    var mashupOneMap = this;
    var mashupObject = new MashupData();

    try { if (_OneMapGlobalToken != '') { token = _OneMapGlobalToken; } } catch (err) { }
    mashupObject.token = token;

    mashupObject.themeName = themeName;
    mashupObject.extent = mashupOneMap.map.extent.xmin + "," + mashupOneMap.map.extent.ymin + "," + mashupOneMap.map.extent.xmax + "," + mashupOneMap.map.extent.ymax;

    var _OneMapThemeGraphicsLayer = new esri.layers.GraphicsLayer();

    _OneMapThemeGraphicsLayer.id = themeName

    mashupOneMap.map.addLayer(_OneMapThemeGraphicsLayer);

    mashupObject.graphicLayer = _OneMapThemeGraphicsLayer;
    mashupObject.GetMashupData(overlayData_1)

    mashupOneMap.onOneMapExtentChange(mashupDataOnExtentChnage)
    return _OneMapThemeGraphicsLayer
}


function overlayData_1(mashupResults) {

    var results = mashupResults.results;

    if (results == "No results") {
        return
    }

    var featcount = mashupResults.count;
    var themeName = mashupResults.theme
    var calloutFields = mashupResults.calloutFields;
    var calloutURL = mashupResults.calloutURL;
    var calloutUniqueFld = mashupResults.calloutUniqueFld;
    var iconPath = mashupResults.iconPath
    var featType = mashupResults.featType;
    var color = mashupResults.color
    var outlineColor = mashupResults.outlineColor
    var lineThickness = mashupResults.lineThickness
    var themeGraphicsLayer_1 = mashupResults.graphicLayer

    themeGraphicsLayer_1.clear();

    if (results.length == 0) {
        return
    }


    for (var i = 0; i < results.length; i++) {
        if (calloutURL != "") {
            calloutURL = calloutURL.replace("intranet.onemap.gov.sg", "www.onemap.sg")

            var multipleDataURL = calloutURL + results[i][calloutUniqueFld.toUpperCase()]
            var nameVal = "";
            nameVal = "<iFrame src='" + multipleDataURL + "'></iFrame>"
            infoTemplate1.setContent(nameVal);
        }
        else {
            var infoTemplate1 = new esri.InfoTemplate();
            infoTemplate1.setTitle(themeName);
            var nameVal = "";
            nameVal += "<strong>" + results[i]["NAME"] + "</strong>" + "<br/>"
            nameVal += results[i]["DESCRIPTION"] + "<br/>"
            nameVal += "<a href=" + results[i]["HYPERLINK"] + " target='_blank'>More Info</a>"
            nameVal += "<img src=" + results[i]["PHOTOURL"] + "></img>" + "<br/>"
            nameVal += results[i]["ADDRESSBLOCKHOUSENUMBER"] + " " + results[i]["ADDRESSSTREETNAME"] + " " + results[i]["ADDRESSBUILDINGNAME"] + " " + results[i]["ADDRESSFLOORNUMBER"] + " " + results[i]["ADDRESSUNITNUMBER"] + "<br/>"
            nameVal += "Singapore " + results[i]["ADDRESSPOSTALCODE"] + "<br/>"
            infoTemplate1.setContent(nameVal);
        }
        var graphic;
        if (featType.toUpperCase() == "LINE") {
            graphic = generateLineGraphic(results[i].XY, color, lineThickness)
        }
        else if (featType.toUpperCase() == "POLYGON") {
            graphic = generatePolygonGraphic(results[i].XY, color, outlineColor, lineThickness)
        }
        else if (featType.toUpperCase() == "POINT") {
            graphic = generatePointGraphic(results[i].XY, results[i].ICON_NAME, iconPath)
        }

        graphic.setInfoTemplate(infoTemplate1);
        themeGraphicsLayer_1.add(graphic);
    }
}


function generatePointGraphic(XY, iconNam, iconPath) {
    var coords = new Array();
    coords = XY.split(",")
    var xCord = coords[0]
    var yCord = coords[1]
    //var iconNam = singleRecord.ICON_NAME
    var iconURL = iconPath + iconNam//singleRecord.ICON_NAME
    if (iconURL != "") {
        iconURL = iconURL.replace("www.onemap.sg", "www.onemap.sg")
        var thmSymbol = new esri.symbol.PictureMarkerSymbol(iconURL, 20, 20)
    }
    var PointLocation = new esri.geometry.Point(xCord, yCord, new esri.SpatialReference({ wkid: 3414 }))
    var PointGraphic = new esri.Graphic(PointLocation, thmSymbol);
    return PointGraphic
}


function generateLineGraphic(linePath, color, lineThickness) {

    var splitLine = linePath.toString().split("|");
    var point1;
    var lineSymbol = new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color(color.split(",")), lineThickness);

    var points = new Array(splitLine.length);
    var LineArr = new Array;
    for (var l = 0; l < splitLine.length; l++) {
        point1 = new esri.geometry.Point(splitLine[l].split(",")[0], splitLine[l].split(",")[1], new esri.SpatialReference({ wkid: 3414 }))
        LineArr.push(point1)
    }
    var polyline = new esri.geometry.Polyline(new esri.SpatialReference({ wkid: 3414 }))
    polyline.addPath(LineArr)

    var lngraphic = new esri.Graphic(polyline, lineSymbol);
    return lngraphic//ln
}


function generatePolygonGraphic(polyPath, color, outlineColor, lineThickness) {

    var pntArr = new Array;
    var poly;
    var polysymbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color(outlineColor.split(",")), lineThickness), new dojo.Color((color + ",0.5").split(",")));

    xyArr = polyPath.toString().split("|");
    for (j = 0; j < xyArr.length; j++) {
        poly = new esri.geometry.Point(xyArr[j].split(",")[0], xyArr[j].split(",")[1], new esri.SpatialReference({ wkid: 3414}))
        pntArr.push(poly)
    }
    var polygon = new esri.geometry.Polygon(new esri.SpatialReference({ wkid: 3414}));
    polygon.addRing(pntArr);
    var polygraphic1 = new esri.Graphic(polygon, polysymbol);
    return polygraphic1
}




function OneMapLayerInfo(layerName) {

    this.themeName = layerName;
    this.GetLayerInfo = GetLayerInfo;
}

function GetLayerInfo(callback) {

    layerName = this.themeName
    var url = host + "API/services.svc/layerinfo?token=" + token + "layername=" + layerName;

    xmlhttp = GetXmlHttpObject();
    if (xmlhttp == null) {
        return;
    }

    restURL = url;

    $.getJSON(url + "&callback=?", function(parsedJSON) {
        var outPutResults = new Object;
        var firstRecord = parsedJSON.LayerInfo[0]
        outPutResults.Category = firstRecord.CATEGORY
        outPutResults.FeatureType = firstRecord.FEATTYPE;
        outPutResults.MinLevel = firstRecord.MINLEVEL
        outPutResults.MaxLevel = firstRecord.MAXLEVEL
        outPutResults.IconPath = firstRecord.IconPath
        outPutResults.AgencyName = firstRecord.AGENCY
        outPutResults.FieldNames = firstRecord.FIELD_NAM_T
        outPutResults.Icon = firstRecord.ICON_NAM_T
        outPutResults.MapTipFieldName = firstRecord.FIELD_NAM_T.split(",")[0];
        outPutResults.calloutURL = firstRecord.CALLOUTURL;
        outPutResults.calloutUniqueFld = firstRecord.CALLOUTFIELDNAME;
        outPutResults.visibleFields = firstRecord.SHOWNATTRIBS;
        outPutResults.pointColour = firstRecord.COLOR_T;

        if (outPutResults.results.length == 0) { outPutResults.results = "No results"; }
        callback(outPutResults);
    });


}


//-------------- mashupTheme end -------------------------------------------------

function nullCheck(input) {
    if (input == undefined)
        return '-'
    if (input == "")
        return '-'
    return input
}



function CoordConvertor() {
    this.ConvertCoordinate = ConvertCoordinate
}

function ConvertCoordinate(i,inputXYList, inputSR, outputSR, callback) {debugger;
	var host2= "http://tasks.arcgisonline.com/";
	var gsvc = new esri.tasks.GeometryService(host + "arcgis/rest/services/Utilities/Geometry/GeometryServer");
    var graphic
    var nsr = new esri.SpatialReference({ wkid: outputSR });

    if (inputXYList.split(",").length == 2) {
        var pointSymbol = new esri.symbol.SimpleMarkerSymbol().setSize(10).setColor(new dojo.Color([255, 0, 0]));
        var geomPoint = new esri.geometry.Point(inputXYList.split(",")[0], inputXYList.split(",")[1], new esri.SpatialReference({ wkid: inputSR }));
        graphic = new esri.Graphic(geomPoint, pointSymbol);

        gsvc.project([geomPoint], nsr, function(outXY) {
		
		var xy = outXY[0].x + "," + outXY[0].y;
		callback(i,outXY[0].x + "," + outXY[0].y)
		 //callback(outXY[0].geometry.x + "," + outXY[0].geometry.y)
			//added for lat - long
			//callback(outXY[0].geometry.y + "," + outXY[0].geometry.x)
        })
    }
    else {
        graphic = getLineGraphic(inputXYList, inputSR)
        gsvc.project([graphic], nsr, function(outXY) {

            var geometry = outXY[0].geometry.paths[0];
            var outputGeoms = "";

            for (var l = 0; l < geometry.length; l++) {
                outputGeoms = outputGeoms + "," + geometry[l][0] + "," + geometry[l][1]
            }
            if (outputGeoms != "") { outputGeoms = outputGeoms.substring(1) }
            callback(outputGeoms)
        })
    }
}


function getLineGraphic(linePath, inputSR) {
    //var linePath = singleRecord.XY;
    var splitLine = linePath.toString().split(",");
    var point1;

    var points = new Array(splitLine.length);
    var LineArr = new Array;
    for (var l = 0; l < splitLine.length; l = l + 2) {
        point1 = new esri.geometry.Point(splitLine[l], splitLine[l + 1], new esri.SpatialReference({ wkid: inputSR }))
        LineArr.push(point1)
    }
    var polyline = new esri.geometry.Polyline(new esri.SpatialReference({ wkid: inputSR }))
    polyline.addPath(LineArr)
    var lngraphic = new esri.Graphic();
    lngraphic.geometry = polyline;

    return lngraphic
};



//Get Url Response for REST Samples only

function GetURLResponse(url, callback) {


    $.getJSON(url + "&callback=?", function(parsedJSON) {

        var outPutResults = "";
        outPutResults = parsedJSON.toString();

        results(outPutResults);

    })
}

function RegisterAPI() {

this.token = "";
this.name = "";
this.email = "";
this.phone = "";
this.url = "";
this.authkey = "";
this.commUse = "";
this.GetRegisterAPIResults = GetRegisterAPIResults;
}


function GetRegisterAPIResults(callback) {

var obj = this;

var token = obj.token;
try { if (_OneMapGlobalToken != '') { token = _OneMapGlobalToken; } } catch (err) { }
var token = obj.token;
var nam = obj.name;
var email = obj.email;
var phone = obj.phone;
var url = obj.url;
var authKey = obj.authkey;
var commercialUse = obj.commUse;


url = host + "RegisterAPI/services.svc/registerApi?url="+url+"&usrname="+nam+"&email="+email+"&phone="+phone+"&commercialuse="+commercialUse+"&authkey="+authKey;

$.getJSON(url + "&callback=?", function(parsedJSON) {
var outPutResults = new Object;
outPutResults.results = parsedJSON.Results[0].Message;
callback(outPutResults);
});
}

/// Don't write anything below this
 
var _OneMapGlobalToken='';document.write('<link rel="stylesheet" type="text/css" href="http://t1.onemap.sg/om_js/arcgis_js_api/library/2.8/arcgis/js/dojo/dijit/themes/tundra/tundra.css"/>');document.write('<script type="text/javascript" src="http://t1.onemap.sg/om_js/arcgis_js_api/library/2.8/arcgis/js/default.js"></script>');
//Localhost token
var _OneMapGlobalToken='xkg8VRu6Ol+gMH+SUamkRIEB7fKzhwMvfMo/2U8UJcFhdvR4yN1GutmUIA3A6r3LDhot215OVVkZvNRzjl28TNUZgYFSswOi';var projSys='';