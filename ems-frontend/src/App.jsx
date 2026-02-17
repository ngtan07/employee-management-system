import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ListEmployeeComponent from './components/ListEmployeeComponent'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <ListEmployeeComponent />
    </>
  )
}

export default App
