// replace timer related functions
window.setTimeout = Async.setTimeout;
window.clearTimeout = Async.clearTimeout;

/**
 * Polyfill for matches(selector).
 */
Element.prototype.matches = function(selector) {
  var elements = document.querySelectorAll(selector);

  for (var i=0, length=elements.length; i<length; i++) {
    if (elements[i] === this) {
      return true;
    }
  }
  return false;
};