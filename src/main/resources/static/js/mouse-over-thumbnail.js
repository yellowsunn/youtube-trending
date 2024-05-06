function mouseOverThumbnail(className, richThumbnail) {
  if (!richThumbnail) {
    return
  }

  const element = document.querySelector(`.${className}`)
  element.setAttribute("src", richThumbnail)
}

function mouseLeaveThumbnail(className, thumbnail) {
  const element = document.querySelector(`.${className}`)

  element.setAttribute("src", thumbnail)
}
