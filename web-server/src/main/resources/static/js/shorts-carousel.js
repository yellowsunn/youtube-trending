function moveShortsCarousel(direction) {
  const element = document.querySelector(".move-shorts")
  if (!element) {
    return

  }
  const translateRegex = /translateX\((-?\d+)px\)/
  let num = Number(element.style.transform.match(translateRegex)[1])
  if (isNaN(num)) {
    return;
  }

  const eachStep = 214
  const maxRight = 1926

  if (direction === "left") {
    num = Math.min(0, num + eachStep * 6)
  } else if (direction === "right") {
    num = Math.max(-maxRight, num - eachStep * 6)
  }

  const leftArrowElement = document.querySelector("#left-arrow .arrow")
  const rightArrowElement = document.querySelector("#right-arrow .arrow")
  element.style.transform = `translateX(${num}px)`

  if (num >= 0) {
    leftArrowElement.classList.add("sf-hidden")
  } else if (num <= -1926) {
    rightArrowElement.classList.add("sf-hidden")
  } else {
    leftArrowElement.classList.remove("sf-hidden")
    rightArrowElement.classList.remove("sf-hidden")
  }
}

