import React, { Component } from "react";
import logo from "./logo.svg";
import "./App.css";

const hateoas = {
  queryParams: (params = {}) => Object.keys(params)
    .map(key => `${encodeURIComponent(key)}=${encodeURIComponent(params[key])}`)
    .join('&'),
  get: (url = "/api/messages", key) =>
    fetch(url, { accept: 'application/json' })
      .then(data => data.json())
      .then(json => json._embedded[key]),
  post: (url = "/api/messages", message) =>
    fetch(url, {
      method: 'post',
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(message)
    }),
};

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = { messages: [] };
    this.onKeyPress = this.onKeyPress.bind(this);
  }

  fetchMessages = () =>
    hateoas.get("/api/messages?sort=modifiedDate,desc&size=1000", "messages")
      .then(messages => this.setState({ messages }));

  sendMessage = message =>
    hateoas.post("/api/v1/messages", message)
      .then(() => this.setState({
        messages: [
          message,
          ...this.state.messages,
        ]
      }));

  onKeyPress = ({ charCode }) => {
    if (13 !== charCode) return;
    this.sendMessage({ body: this.input.value });
    this.input.value = '';
  };

  componentDidMount = () =>
    this.fetchMessages();

  render() {
    return (
      <div className="App">
        <div className="App-header" onClick={this.fetchMessages}>
          <img src={logo} className="App-logo" alt="logo" />
          <h2>Welcome to React</h2>
        </div>
        <input type="text"
               ref={node => this.input = node}
               onKeyPress={this.onKeyPress}/>
        {this.state.messages.map((message, id) => <div className="App-intro"
                                                       key={id}>{message.body}</div>)}
      </div>
    );
  }
}
